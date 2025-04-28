package ru.hpclab.hl.module1.kafka;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import ru.hpclab.hl.module1.kafka.entity.EntityType;
import ru.hpclab.hl.module1.kafka.entity.OperationType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KafkaOperationMessage {
    private EntityType entity;
    private OperationType operation;
    private JsonNode payload;
}