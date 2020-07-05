package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
class MessageListener {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @JmsListener(destination = "PAYMENT2")
    fun listen(message: OrderMessage) {
        logger.info("Consumer Listen - > $message")
        val err = 1 / 0
    }
}

data class OrderMessage(
        val payload: String = ""
) : Serializable