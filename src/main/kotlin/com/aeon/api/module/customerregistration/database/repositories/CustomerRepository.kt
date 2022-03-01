package com.aeon.api.module.customerregistration.database.repositories

import com.aeon.api.module.customerregistration.database.entities.Customer
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository

interface CustomerRepository : PagingAndSortingRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
}