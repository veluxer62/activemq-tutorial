package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
class MessageSender(private val jmsTemplate: JmsTemplate) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun send(message: OrderMessage) {
        logger.info("Producer Message -> [$message]")
        jmsTemplate.convertAndSend("ORDER", message)
    }
}

data class OrderMessage(
        val payload: String = ""
) : Serializable