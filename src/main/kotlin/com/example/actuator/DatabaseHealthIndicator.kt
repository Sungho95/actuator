package com.example.actuator

import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component

@Component
class DatabaseHealthIndicator : HealthIndicator {
    override fun health(): Health {
        return try {
            // 데이터베이스 연결 체크 시뮬레이션
            val isDbHealthy = checkDatabaseConnection()
            
            if (isDbHealthy) {
                Health.up()
                    .withDetail("database", "PostgreSQL")
                    .withDetail("connection_pool", "10/20 active")
                    .withDetail("last_check", System.currentTimeMillis())
                    .build()
            } else {
                Health.down()
                    .withDetail("error", "Database connection failed")
                    .build()
            }
        } catch (ex: Exception) {
            Health.down(ex).build()
        }
    }
    
    private fun checkDatabaseConnection(): Boolean {
        // 실제 데이터베이스 체크 로직
        return (0..10).random() > 2 // 80% 성공률 시뮬레이션
    }
}

@Component
class ExternalServiceHealthIndicator : HealthIndicator {
    override fun health(): Health {
        return Health.up()
            .withDetail("external_api", "Available")
            .withDetail("response_time", "${(50..200).random()}ms")
            .withDetail("status_code", 200)
            .build()
    }
}