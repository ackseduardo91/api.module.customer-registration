package com.aeon.api.module.customerregistration.modules.customer.service

import com.aeon.api.module.customerregistration.database.entities.Customer
import com.aeon.api.module.customerregistration.models.dtos.CustomerDTO
import com.aeon.api.module.customerregistration.models.dtos.CustomerSaveDTO
import com.aeon.api.module.customerregistration.modules.customer.provider.CustomerProvider
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerServiceImpl(
    private val customerProvider: CustomerProvider,
): CustomerService
{
    override fun saveCustomer(customer: CustomerSaveDTO): String =
        customerProvider.saveCustomer(Customer(customer))

    override fun searchListCustomerPaginatedWithFilter(
        name: String?,
        cpf: String?,
        email: String?,
        pageable: Pageable
    ): Page<CustomerDTO> =
        customerProvider.searchListCustomerPaginatedWithFilter(name, cpf, email, pageable).map { CustomerDTO(it) }

    override fun editCustomer(customerId: UUID, customer: CustomerSaveDTO): String? {
        val oldCustomer = customerProvider.searchCustomerById(customerId)
        return  if (oldCustomer != null) {
            customer.customerId = oldCustomer.customerId
            customerProvider.editCustomer(Customer(customer))
        } else null
    }

    override fun deleteCustomer(customerId: UUID): String? {
        val customerDelete = customerProvider.searchCustomerById(customerId)
        return if (customerDelete != null) customerProvider.deleteCustomer(customerDelete) else null
    }

}