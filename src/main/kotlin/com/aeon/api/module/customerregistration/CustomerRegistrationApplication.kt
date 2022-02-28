package com.aeon.api.module.customerregistration

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CustomerRegistrationApplication

fun main(args: Array<String>) {
	runApplication<CustomerRegistrationApplication>(*args)
}
