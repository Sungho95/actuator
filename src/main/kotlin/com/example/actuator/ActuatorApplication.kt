package com.example.actuator

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@EnableAdminServer
@SpringBootApplication
class ActuatorApplication

fun main(args: Array<String>) {
    runApplication<ActuatorApplication>(*args)
}
