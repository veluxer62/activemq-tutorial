package com.example.demo

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(private val messageSender: MessageSender) {

    @PostMapping("/topic/sampling")
    fun sampleTopic(@RequestBody message: String) {
        messageSender.send(OrderMessage(payload = message))
    }

}