package com.success.oracle.service;


import org.springframework.stereotype.Service;

@Service
public interface TicketService {
        String generateTicket(String username);
        String verifyTicket(String ticket);
}
