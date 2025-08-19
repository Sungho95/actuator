package com.example.actuator

import org.springframework.boot.actuate.info.Info
import org.springframework.boot.actuate.info.InfoContributor
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CustomInfoContributor : InfoContributor {
    override fun contribute(builder: Info.Builder) {
        builder.withDetail("app", mapOf(
            "version" to "1.0.0",
            "environment" to "development",
            "build-time" to LocalDateTime.now().toString(),
            "features" to listOf("actuator", "admin", "metrics")
        ))
        
        builder.withDetail("administrator", mapOf(
            "name" to "Park Sungho",
            "contact" to "없음"
        ))
        
        builder.withDetail("system", mapOf(
            "database" to "example DB",
            "cache" to "Redis XX",
            "message_queue" to "RabbitMQ XX"
        ))
    }
}