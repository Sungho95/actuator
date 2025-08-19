package com.example.actuator

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Timer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(private val meterRegistry: MeterRegistry) {
    
    private val orderCounter = Counter.builder("orders.created")
        .description("Total orders created")
        .register(meterRegistry)
    
    private val processingTimer = Timer.builder("order.processing.time")
        .description("Order processing time")
        .register(meterRegistry)
    
    @GetMapping("/orders")
    fun getOrders(): List<Order> {
        return listOf(
            Order(1, "Order 1", 100.0),
            Order(2, "Order 2", 200.0)
        )
    }
    
    @PostMapping("/orders")
    fun createOrder(@RequestBody order: Order): Order {
        return processingTimer.recordCallable {
            // 주문 처리 시뮬레이션
            Thread.sleep((100..500).random().toLong())
            orderCounter.increment()
            order.copy(id = (1..1000).random())
        }!!
    }
    
    @GetMapping("/simulate-load")
    fun simulateLoad(): String {
        repeat(10) {
            createOrder(Order(0, "Simulated Order $it", (50..500).random().toDouble()))
        }
        return "Load simulation completed"
    }
}

data class Order(val id: Int, val name: String, val amount: Double)