package com.edo.nb.ktsb

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/hello")
class HelloWorldController {

    @GetMapping
    fun helloWorld(): String {
        return "Say Hello To Rest Endpoint!"
    }

    @GetMapping("/v2")
    fun helloWorldV2(): String = "Say Hello To Rest Endpoint V2"
}