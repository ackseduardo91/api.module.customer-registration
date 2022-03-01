package com.aeon.api.module.customerregistration.modules.customer.provider

import com.aeon.api.module.customerregistration.database.entities.Customer
import com.aeon.api.module.customerregistration.database.repositories.CustomerRepository
import com.aeon.api.module.customerregistration.database.specifications.CustomerSpecification
import com.aeon.api.module.customerregistration.models.utils.DataUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CustomerProviderImpl (
    private val customerRepository: CustomerRepository,
): CustomerProvider
{
    override fun saveCustomer(customer: Customer): String {
        val customer = customerRepository.save(customer)
        return "Successfully Registered ( Name: " +
                "${customer.name} - Age: " +
                "${DataUtils().getCustomerAge(customer.birthDate)} )"
    }

    override fun searchListCustomerPaginatedWithFilter(
        name: String?,
        cpf: String?,
        email: String?,
        pageable: Pageable
    ): Page<Customer> {
        val filter: CustomerSpecification = CustomerSpecification()
        return customerRepository.findAll(filter.dynamicSearchFilter(name, cpf, email), pageable)
    }

    override fun editCustomer(customer: Customer): String? {
        customerRepository.save(customer)
        return "Update performed successfully ( Name: " +
                "${customer.name} - Id: " +
                "${customer.customerId} )"
    }

    override fun searchCustomerById(id: Long):Customer? =
        customerRepository.findByIdOrNull(id)

    override fun deleteCustomer(customer: Customer): String? {
        customerRepository.delete(customer)
        return "Deletion performed successfully ( Name: " +
                "${customer.name} - Id: " +
                "${customer.customerId} )"
    }

}