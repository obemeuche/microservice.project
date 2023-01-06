package com.obeme.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("{customerId}/{customerEmail}")
    public void notifyCustomer (@PathVariable("customerId") Integer customerId,@PathVariable("customerEmail") String customerEmail) {
        log.info("Notification for customerId " + customerId);
        notificationService.notify(customerId, customerEmail);
    }
}
