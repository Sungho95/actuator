package com.example.actuator

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LogTestController {
    private val logger = LoggerFactory.getLogger(LogTestController::class.java)
    
    @GetMapping("/generate-logs")
    fun generateLogs(): String {
        logger.info("INFO: Application is running smoothly")
        logger.warn("WARN: This is a warning message for testing")
        logger.error("ERROR: This is an error message for testing")
        logger.debug("DEBUG: Debug information")
        
        return "Logs generated successfully"
    }
    
    @GetMapping("/simulate-error")
    fun simulateError(): String {
        try {
            throw RuntimeException("Simulated exception for testing")
        } catch (e: Exception) {
            logger.error("Caught exception: ${e.message}", e)
            throw e
        }
    }
    
    @Scheduled(fixedRate = 30000)
    fun periodicLog() {
        logger.info("Scheduled task executed at ${System.currentTimeMillis()}")
    }
}