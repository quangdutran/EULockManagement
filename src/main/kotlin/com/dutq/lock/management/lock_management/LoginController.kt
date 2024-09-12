package com.dutq.lock.management.lock_management

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
    @GetMapping("/login")
    fun login(): String? {
        return "login"
    }
}