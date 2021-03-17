## dxl-msk-producer

This project accepts a REST request and sends a message to a managed Kafka cluster 
(MSK)

### Architecture
The application has the following components:


**`AWS Cloudformation Templates`** - 2 templates that build the ecosystem
* `infra_msk_kafka.t3.small.yml` - This template creates a 2 broker MSK cluster
* `infra_msk_asg.yml` - This template creates a load balanced auto-scaling group which hosts the Spring Kafka client

**`Spring Application`** 
A Springboot application that accepts REST requests and sends a Kafka message
 
### Setup
- Log into your AWS account and run `infra_msk_kafka.t3.small.yml` using Cloudformation. Take note of the `Cluster ARN` which is one of the Cloudformation outputs, you will need it in the next stages
- Log into the Bastion host and extract the following certificates for EACH BROKER using `openssl`

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
- Create a `PKCS12 truststore` and add the certs in the previous step, starting with the roots.
- Upload the `truststore` to an S3 bucket which is secure but which you have access. The URL of the truststore on S3 will be required on the `LaunchConfiguration` resource to configure instances that can start automatically
- Run `infra_msk_asg.yml`. Fill in the `Cluster ARN` that you saved on step 1
- If the Cluster is created successfully, the Load Balancer endpoint is generated in the Outputs section