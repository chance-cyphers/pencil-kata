package com.cyphers.chance.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ErrorLogger {

    final static Logger logger = LoggerFactory.getLogger(ErrorLogger.class);

    public void error(String message) {
        logger.error(message);
    }

}
