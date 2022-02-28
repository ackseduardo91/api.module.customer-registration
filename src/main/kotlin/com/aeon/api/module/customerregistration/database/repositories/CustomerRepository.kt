package com.aeon.api.module.customerregistration.database.repositories

import com.aeon.api.module.customerregistration.database.entities.Customer
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface CustomerRepository : PagingAndSortingRepository<Customer, UUID>, JpaSpecificationExecutor<Customer> {
    fun findByCpf(cpf: String?): Optional<Customer>?
}