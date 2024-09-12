package com.dutq.lock.management.lock_management.controllers

import com.dutq.lock.management.lock_management.entites.LockDTO
import com.dutq.lock.management.lock_management.services.LockService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class LockController(private val lockService: LockService) {

    @GetMapping("/locks")
    fun getLocks(): ResponseEntity<List<LockDTO>> {
        return ResponseEntity.ok(lockService.getLocks(20, 1))
    }

    @GetMapping("/lock")
    fun getLock(): ResponseEntity<LockDTO> {
        return ResponseEntity.ok(lockService.getLocks(20, 1)[0])
    }

    @PostMapping("/unlock/{lockId}")
    fun unlock(@PathVariable lockId: String?): ResponseEntity<Void> {
        lockService.unlock(lockId)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/code/{lockId}")
    fun getOpenCode(@PathVariable lockId: String?): ResponseEntity<String> {
        return ResponseEntity.ok(lockService.getOpenCode(lockId))
    }
}
