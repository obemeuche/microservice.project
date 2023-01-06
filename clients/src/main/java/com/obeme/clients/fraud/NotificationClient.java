package com.obeme.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient ("NOTIFICATION")
public interface NotificationClient {

    @PostMapping("api/v1/notification/{customerId}/{customerEmail}")
    void notifyCustomer (@PathVariable("customerId") Integer customerId,@PathVariable("customerEmail") String customerEmail);
}
