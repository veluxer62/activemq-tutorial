package com.example.demo

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(private val messageSender: MessageSender) {

    @PostMapping("/topic/sampling")
    fun sampleTopic(@RequestBody message: String) {
        messageSender.sendTopic(SampleData(body = message))
    }

    @PostMapping("/virtual/sampling")
    fun sampleVirtual(@RequestBody message: String) {
        messageSender.sendVirtual(SampleData(body = message))
    }

}