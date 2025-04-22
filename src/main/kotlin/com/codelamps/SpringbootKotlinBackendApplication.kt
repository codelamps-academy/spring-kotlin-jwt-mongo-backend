package com.codelamps

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringbootKotlinBackendApplication

fun main(args: Array<String>) {
	runApplication<SpringbootKotlinBackendApplication>(*args)
}
