package com.aeon.api.module.customerregistration.modules.customer.provider

import com.aeon.api.module.customerregistration.database.entities.Customer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface CustomerProvider {
    fun saveCustomer(customer: Customer): String
    fun searchListCustomerPaginatedWithFilter(
        name: String?,
        cpf: String?,
        email: String?,
        pageable: Pageable
    ): Page<Customer>

    fun editCustomer(customer: Customer): String?
    fun searchCustomerById(id: UUID): Customer?
    fun deleteCustomer(customer: Customer): String?
}