package com.obeme.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void notify (Integer customerId) {

        notificationRepository.save(
                Notification.builder()
                        .customerId(customerId)
                        .message("Thank you for believing in us! We hope to serve you better")
                        .time(LocalDateTime.now())
                        .build());
    }
}
