package com.success.oracle.service.impl;

import com.success.oracle.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@Service
public class TicketServiceImpl implements TicketService {

    private final Map<String, String> ticketStore = new ConcurrentHashMap<>();

    @Override
    public String verifyTicket(String ticket) {
        String username = ticketStore.remove(ticket);
        log.info("验证 Ticket: {}, 用户: {}", ticket, username);
        return username;
    }

    @Override
    public String generateTicket(String username) {
        String ticket = UUID.randomUUID().toString().replace("-", "");
        ticketStore.put(ticket, username);
        log.info("生成 Ticket: {}", ticket);
        return ticket;
    }
}
