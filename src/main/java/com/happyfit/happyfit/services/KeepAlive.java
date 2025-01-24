package com.happyfit.happyfit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.happyfit.happyfit.models.User;

import com.happyfit.happyfit.seeders.DatabaseSeeder;

import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KeepAlive {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(KeepAlive.class);

    @Transactional
    @Scheduled(cron = "0 0,12,24,36,48 7-23 * * *")
    public void keepAlive() {
        logger.info("KEEP ALIVE CRON");
        User user = userService.findByEmail("admin@example.com");
        if (user != null) logger.info("KEEP ALIVE SUCCESFULLY FETCHED '" + user.getName() + "' USER");
        else logger.error("KEEP ALIVE FAILED TO FETCH USER");
    }
}
