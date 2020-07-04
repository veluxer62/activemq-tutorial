package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    val app = runApplication<DemoApplication>(*args)
    val sender = app.getBean(MessageSender::class.java)

    (1..10).forEach {
        sender.send(OrderMessage(payload = "test-message-${it}"))
    }
}
