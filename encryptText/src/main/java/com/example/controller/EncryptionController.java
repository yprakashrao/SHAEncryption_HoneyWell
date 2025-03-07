package com.example.controller;

import com.example.service.EncryptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class EncryptionController {
    
    private static final Logger logger = LoggerFactory.getLogger(EncryptionController.class);
    
    @Autowired
    private EncryptionService encryptionService;
    
    @PostMapping("/encrypt")
    public String encryptText(@RequestBody String text, @RequestParam("salt") String salt) {
        logger.info("Received encryption request: text='{}', salt='{}'", text, salt);
        String encrypted = encryptionService.encrypt(text, salt);
        logger.info("Returning encrypted text: '{}'", encrypted);
        return encrypted;
    }
    
    @PostMapping("/encrypt2")
    public String encryptText2(@RequestBody String text, @RequestParam("salt") String salt) {
        logger.info("Received encryption request: text='{}', salt='{}'", text, salt);
        String encrypted = encryptionService.encrypt2(text, salt);
        logger.info("Returning encrypted text: '{}'", encrypted);
        return encrypted;
    }
}