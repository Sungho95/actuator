package com.example.actuator

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiTestController {
    
    @GetMapping("/api/users")
    fun getUsers(): List<String> {
        Thread.sleep((10..100).random().toLong()) // 응답시간 시뮬레이션
        return listOf("User1", "User2", "User3")
    }
    
    @GetMapping("/api/products")
    fun getProducts(): List<String> {
        Thread.sleep((50..200).random().toLong())
        return listOf("Product1", "Product2")
    }
    
    @PostMapping("/api/data")
    fun postData(@RequestBody data: Map<String, Any>): Map<String, Any> {
        Thread.sleep((20..150).random().toLong())
        return mapOf("status" to "success", "data" to data)
    }
    
    @GetMapping("/api/slow")
    fun slowEndpoint(): String {
        Thread.sleep(2000) // 느린 엔드포인트 시뮬레이션
        return "Slow response"
    }
}