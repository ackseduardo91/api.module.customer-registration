package com.aeon.api.module.customerregistration.modules.customer.service

import com.aeon.api.module.customerregistration.models.dtos.CustomerSaveDTO
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity

interface CustomerService {
    fun saveCustomer(customer: CustomerSaveDTO): ResponseEntity<Any>
    fun searchListCustomerPaginatedWithFilter(
        name: String?,
        cpf: String?,
        email: String?,
        pageable: Pageable
    ): ResponseEntity<Any>

    fun editCustomer(customerId: Long, customer: CustomerSaveDTO): ResponseEntity<Any>
    fun deleteCustomer(customerId: Long): ResponseEntity<Any>
}