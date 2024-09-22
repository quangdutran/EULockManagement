package com.dutq.lock.management.lock_management.controllers

import com.dutq.lock.management.lock_management.dtos.CheckinRequest
import com.dutq.lock.management.lock_management.dtos.ProfitByMonth
import com.dutq.lock.management.lock_management.entites.Stay
import com.dutq.lock.management.lock_management.services.MainService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class MainController(private val mainService: MainService) {
    @GetMapping("/stay/{lockId}")
    fun getStayByLockId(@PathVariable lockId: Int): ResponseEntity<Stay> {
        return mainService.getCurrentStayByLockId(lockId).map { body: Stay? -> ResponseEntity.ok(body) }
            .orElse(ResponseEntity.noContent().build())
    }

    @GetMapping("/stays")
    fun getStayByLockId(@RequestParam("from") from: Long, @RequestParam("to") to: Long): ResponseEntity<List<Stay>> {
        return ResponseEntity.ok(mainService.getAllStays(from, to))
    }

    @PostMapping("/stay/checkin")
    fun checkin(@RequestBody checkinRequest: @Valid CheckinRequest?): ResponseEntity<Stay> {
        return ResponseEntity.ok(mainService.checkin(checkinRequest))
    }

    @PostMapping("/stay/{stayId}/checkout")
    fun checkout(@PathVariable stayId: Int): ResponseEntity<Stay> {
        val checkout = mainService.checkoutStay(stayId.toLong())
        return ResponseEntity.ok(checkout)
    }

    @GetMapping("/stays/{year}")
    fun getProfitByYearGroupByMoth(@PathVariable year: Int): ResponseEntity<List<ProfitByMonth>> {
        return ResponseEntity.ok(mainService.getProfitByYear(year))
    }

    @GetMapping("/healthCheck")
    fun getHealth(): ResponseEntity<String> {
        return ResponseEntity.ok("OK")
    }
}
