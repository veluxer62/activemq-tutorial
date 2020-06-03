package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
class MessageListener {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @JmsListener(destination = "VirtualTopic.TEST")
    fun listen3(message: SampleData) {
        logger.info("Server3 Virtual1 - > $message")
    }

    @JmsListener(destination = "VirtualTopic.TEST")
    fun listen4(message: SampleData) {
        logger.info("Server3 Virtual2 - > $message")
    }

//    @JmsListener(destination = "Consumer.test3.VirtualTopic.TEST")
//    fun listen5(message: SampleData) {
//        logger.info("Server2 Virtual1 - > $message")
//    }

    @JmsListener(destination = "Consumer.test3.VirtualTopic.TEST")
    fun listen6(message: SampleData) {
//        val err = 1 / 0
        logger.info("Server2 Virtual2 - > $message")
    }
}

data class SampleData(
        val body: String = ""
) : Serializable