package com.aeon.api.module.customerregistration.modules.customer.controller

import com.aeon.api.module.customerregistration.models.dtos.CustomerSaveDTO
import com.aeon.api.module.customerregistration.modules.customer.service.CustomerService
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Validated
@RestController
@RequestMapping("/api/customer")
class CrudCustomerController(
    private val customerService: CustomerService
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = ["application/json"])
    fun searchListCustomerPaginatedWithFilter(
        @RequestParam(name = "name", required = false) name: String?,
        @RequestParam(name = "cpf", required = false) cpf: String?,
        @RequestParam(name = "email", required = false) email: String?,
        @RequestParam(name = "page",defaultValue = "0") page: Int,
        @RequestParam(name = "size",defaultValue = "10") size: Int): ResponseEntity<Any> =
            customerService.searchListCustomerPaginatedWithFilter(name, cpf, email, PageRequest.of(page, size))

    @PostMapping("/new", produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun saveCustomer(
        @RequestBody @Valid customer: CustomerSaveDTO): ResponseEntity<Any> =
            customerService.saveCustomer(customer)


    @PatchMapping("/{customerId}/edit", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    fun editCustomer(
        @PathVariable @NotNull customerId: Long,
        @Valid @RequestBody customer: CustomerSaveDTO): ResponseEntity<Any> =
            customerService.editCustomer(customerId, customer)

    @DeleteMapping("/{customerId}/delete", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    fun deleteCustomer(@PathVariable @NotNull customerId: Long): ResponseEntity<Any> =
        customerService.deleteCustomer(customerId)
}