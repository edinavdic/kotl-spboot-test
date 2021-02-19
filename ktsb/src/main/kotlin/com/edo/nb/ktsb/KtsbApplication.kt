package com.edo.nb.ktsb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@SpringBootApplication
class KtsbApplication

fun main(args: Array<String>) {
	runApplication<KtsbApplication>(*args)
}

@RestController
class RestController {

	@GetMapping("/stocks/{symbol}")
	fun prices(@PathVariable symbol: String) : StockPrice {
		return StockPrice("222", 22.2, LocalDateTime.now())
	}

	private fun randomStockPrice(): Double {
		return 0.0
	}
}

data class StockPrice(val symbol: String,
					  val price: Double,
					  val time: LocalDateTime)
