package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDateTime

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    val app = runApplication<DemoApplication>(*args)
    val sender = app.getBean(MessageSender::class.java)

    sender.send(OrderMessage(payload = "test-message[${LocalDateTime.now()}]"))
}
