package com.success.oracle.service.impl;

import com.success.oracle.service.SsoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class SsoServiceImpl implements SsoService {

    private static final String SSO_SERVER = "http://localhost:8080";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String verifyTicket(String ticket) {
        try {
            String url = SSO_SERVER + "/verifyTicket?ticket=" + ticket;
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            log.error("验证 Ticket 失败: {}", e.getMessage());
            return null;
        }
    }
}