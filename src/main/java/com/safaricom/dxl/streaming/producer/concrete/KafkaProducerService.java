package com.safaricom.dxl.streaming.producer.concrete;

import com.safaricom.dxl.streaming.producer.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducerService implements ProducerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    @Value(value = "${listener.topic}")
    private String topic;

    public void sendMessage(String message) {
        final ListenableFuture<SendResult<String, String>> sendFuture = template.send(this.topic, message);
        sendFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info(String.format("Sent message='%s' with offset=%d", message, result.getRecordMetadata().offset()));
                System.out.printf("Sent message=%s with offset=%d", message, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.info(String.format("Failed message='{}' with error={}", message, ex));
                System.out.printf("Failed message=%s with error=%s", message, ex);
            }
        });
    }
}
