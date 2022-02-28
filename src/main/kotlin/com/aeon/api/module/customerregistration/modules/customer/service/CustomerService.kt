package com.aeon.api.module.customerregistration.modules.customer.service

import com.aeon.api.module.customerregistration.models.dtos.CustomerDTO
import com.aeon.api.module.customerregistration.models.dtos.CustomerSaveDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface CustomerService {
    fun saveCustomer(customer: CustomerSaveDTO): String
    fun searchListCustomerPaginatedWithFilter(
        name: String?,
        cpf: String?,
        email: String?,
        pageable: Pageable
    ): Page<CustomerDTO>

    fun editCustomer(customerId: UUID, customer: CustomerSaveDTO): String?
    fun deleteCustomer(customerId: UUID): String?
}