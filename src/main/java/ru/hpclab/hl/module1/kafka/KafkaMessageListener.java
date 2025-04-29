package ru.hpclab.hl.module1.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaMessageListener {

    private final ObjectMapper objectMapper;
    private final KafkaMessageDispatcher kafkaMessageDispatcher;

    private static final Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(
            topics = "${kafka.topic:var16}",
            groupId = "${kafka.groupId:sergeevvs-consumer-group}",
            concurrency = "${kafka.concurrency:2}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleMessage(String messageJson) {
        log.info("Получено сообщение из Kafka: {}", messageJson);

        try {
            KafkaOperationMessage message = objectMapper.readValue(messageJson, KafkaOperationMessage.class);

            //log.info("Передаётся на выполнение: entity={}, operation={}",
            //        message.getEntity(), message.getOperation());

            kafkaMessageDispatcher.dispatch(message);

            log.info("Сообщение обработано успешно: entity={}, operation={}",
                    message.getEntity(), message.getOperation());
        } catch (Exception e) {
            log.error("Ошибка при обработке сообщения Kafka: {}", messageJson, e);
        }
    }
}