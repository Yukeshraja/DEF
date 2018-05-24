package com.altimetrik.def.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.altimetrik.def.async.LogService;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final LogService logService;

    public AppRunner(LogService logService) {
        this.logService = logService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();



    }

}
