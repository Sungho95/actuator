package com.example.actuator

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class BusinessMetrics(private val meterRegistry: MeterRegistry) {
    
    @Scheduled(fixedRate = 5000)
    fun updateMetrics() {
        // 모든 메트릭을 스케줄링으로 업데이트 - 이 방식이 가장 안정적
        meterRegistry.gauge("business.active.users", getActiveUserCount().toDouble())
        meterRegistry.gauge("custom.memory.usage", getCustomMemoryUsage())
        meterRegistry.gauge("business.revenue.today", getTodayRevenue())
        meterRegistry.gauge("business.cpu.custom", getCustomCpuUsage())
    }
    
    private fun getActiveUserCount(): Int = (100..500).random()
    private fun getTodayRevenue(): Double = (1000..5000).random().toDouble()
    private fun getCustomMemoryUsage(): Double = Runtime.getRuntime().let {
        (it.totalMemory() - it.freeMemory()).toDouble() / 1024 / 1024
    }
    private fun getCustomCpuUsage(): Double = (10..80).random().toDouble()
}