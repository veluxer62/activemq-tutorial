package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
class MessageSender(private val jmsTemplate: JmsTemplate) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun sendTopic(message: SampleData) {
        logger.info("Sender Message -> [$message]")
        jmsTemplate.convertAndSend("TOPIC.TEST", message)
    }

    fun sendVirtual(message: SampleData) {
        logger.info("Sender Message -> [$message]")
        jmsTemplate.convertAndSend("VirtualTopic.TEST", message)
    }
}

data class SampleData(
        val body: String = ""
) : Serializable