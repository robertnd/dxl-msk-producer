## dxl-msk-producer

This project accepts a REST request and sends a message to a managed Kafka cluster 
(MSK)

### Architecture
The application has the following components:


**`AWS Cloudformation Templates`** 

`1. infra_bucket.yml` - This template creates a secure bucket. This bucket holds files necessary for the configuration of the clients. These files are
 
 (1)  Application Jar file 
 
 (2) Truststore (see details on creating the truststore in the sections following)
 
 (3) a script that runs the application (scripts/runRestClient.sh)
 

`2. infra_msk_kafka.t3.small.yml` - This template creates a 2 broker MSK cluster with a Bastion Host. The template installs Java and Kafka in the Bastion. 
These are necessary to create topics in the cluster which is a manual process in this setup

`3. infra_msk_asg.yml` - This template creates a load balanced auto-scaling group which hosts the Spring Kafka client

`4. infra_msk_sharedVPC_asg.yml` - This template is simiilar in functionality to `infra_msk_asg.yml`. It creates a load balanced auto-scaling group which hosts the Spring Kafka client in the same VPC that contains the MSK cluster. 
Sharing a VPC with the MSK cluster eliminates the need to create an independent VPC, peering operations and reduces on the number of Elastic IPs consumed. Please note, only run one template


**`Spring Application`** 

A Springboot application that accepts REST requests and sends a Kafka message
 
### Setup
- 0: Preliminaries: 2 VPCs are required. Take due care in the CIDR you select and assign for the VPCs and their subnets. They cannot overlap
- 1: Log into your AWS account and in Cloudformation create a stack using `infra_bucket.yml`. This creates a resources bucket. You will upload files to this bucket which will be used in config operations
- 2: In Cloudformation create a stack using `infra_msk_kafka.t3.small.yml`. This creates an MSK cluster and takes approximately 15 to 20 minutes. 
- 3: Once successfully created, open the MSK Console and navigate to the new cluster. Take note of the (1) zookeeper list and (2) broker list (can be found in the View Client Information panel on the current interface). The broker list is required when running the next template
- 4: Use the private key you selected and log into the Bastion Host.
- 5: Use the installed Kafka package to create a new topic in the cluster. You will need the Zookeeper node list

    ```
    /<kafka-dir>/bin/kafka-topics.sh --create --zookeeper <zookeeper-connection-string> --replication-factor 2 --partitions 1 --topic <topic> 
    
    ```
    
- 6: Log into the Bastion host and extract broker certificates using the command `openssl s_client -connect <broker-connection-string>`. The output is the same for all brokers in the cluster. The command prints 4 certificates with the chain shown below. Copy each certificate text into a separate file and save it as a PEM file (.pem)

NB: Brokers are inaccessible from anywhere else other than in their VPC or peered / transitive VPCs. The openssl command cannot connect from your local machine
 

```
Certificate chain
    0 s:/CN=*.<broker-DNS-Name>
    i:/C=US/O=Amazon/OU=Server CA 1B/CN=Amazon

    1 s:/C=US/O=Amazon/OU=Server CA 1B/CN=Amazon
    i:/C=US/O=Amazon/CN=Amazon Root CA 1

    2 s:/C=US/O=Amazon/CN=Amazon Root CA 1
    i:/C=US/ST=Arizona/L=Scottsdale/O=Starfield Technologies, Inc./CN=Starfield Services Root Certificate Authority - G2

    3 s:/C=US/ST=Arizona/L=Scottsdale/O=Starfield Technologies, Inc./CN=Starfield Services Root Certificate Authority - G2
    i:/C=US/O=Starfield Technologies, Inc./OU=Starfield Class 2 Certification Authority

```
- 7: Create a `PKCS12 truststore` and add the PEM files saved in the previous step
- 8: Copy the `scripts/runRestClient.sh.template` into a `runRestClient.sh` script outside of the project directory and modify to add the `truststore` password. Take care not to expose sensitive information in your repositories 
- 9: Upload the `truststore`, the `application jar file` and the `scripts/runRestClient.sh` to the S3 bucket created earlier. Do NOT grant public access to the bucket or the items 

NOTE 1: The cluster needs to be running, topics configured, and application jar, truststore and script uploaded to S3 to commence the next step.


- 10: Modify the `infra_msk_asg.yml` template - or alternatively the `infra_msk_sharedVPC_asg.yml` template - with the name of the Bucket created in Step 1. 
- 11: In Cloudformation create a stack using `infra_msk_asg.yml` or `infra_msk_sharedVPC_asg.yml` 
- 12: In the Broker input, provide the Broker list you saved earlier in (step 4)
- 13: In the MSKCluster input, provide the `StackName` you provided when creating the stack in Step 2. This template is dependent on outputs from that stack to function correctly
- 14: If the Cluster is created successfully, the Load Balancer endpoint is generated in the Outputs section
- 15: Use this Endpoint to send requests to MSK