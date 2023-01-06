package com.obeme.notification;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Notification {
    @Id
    @SequenceGenerator(
            name = "notification_id_sequence",
            sequenceName = "notification_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_id_sequence"
    )
    private Integer Id;
    private Integer customerId;
    private String customerEmail;
    private String sender;
    private String message;
    private LocalDateTime time;
}
