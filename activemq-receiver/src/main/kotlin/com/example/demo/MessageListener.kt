package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
class MessageListener {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @JmsListener(destination = "TOPIC.TEST")
    fun listen(message: SampleData) {
        logger.info("Server1 Listen - > $message")
    }

    @JmsListener(destination = "TOPIC.TEST")
    fun listen2(message: SampleData) {
        logger.info("Server1 Listen2 - > $message")
    }

    @JmsListener(destination = "Consumer.test.VirtualTopic.TEST")
    fun listen3(message: SampleData) {
        logger.info("Server1 Virtual1 - > $message")
    }

    @JmsListener(destination = "Consumer.test.VirtualTopic.TEST")
    fun listen4(message: SampleData) {
        logger.info("Server1 Virtual2 - > $message")
    }
}

data class SampleData(
        val body: String = ""
) : Serializable