package com.aeon.api.module.customerregistration.modules.customer.service

import com.aeon.api.module.customerregistration.database.entities.Customer
import com.aeon.api.module.customerregistration.models.dtos.CustomerDTO
import com.aeon.api.module.customerregistration.models.dtos.CustomerSaveDTO
import com.aeon.api.module.customerregistration.models.dtos.ResponseApiDTO
import com.aeon.api.module.customerregistration.models.error.CustomGlobalExceptionHandler
import com.aeon.api.module.customerregistration.modules.customer.provider.CustomerProvider
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CustomerServiceImpl(
    private val customerProvider: CustomerProvider,
): CustomerService
{
    override fun saveCustomer(customer: CustomerSaveDTO): ResponseEntity<Any> =
        try {
            ResponseEntity.ok(
                ResponseApiDTO(
                    LocalDateTime.now(),
                    HttpStatus.CREATED.toString(),
                    customerProvider.saveCustomer(Customer(customer, LocalDateTime.now(), null)),
                    null
                )
            )
        }catch (ex: Exception){
            ResponseEntity.badRequest().body(
                ex.cause?.cause?.message?.let { CustomGlobalExceptionHandler().customHandleBadRequest(it) }
            )
        }

    override fun searchListCustomerPaginatedWithFilter(
        name: String?,
        cpf: String?,
        email: String?,
        pageable: Pageable
    ): ResponseEntity<Any> =
        try {
            val listCustomer = customerProvider.searchListCustomerPaginatedWithFilter(name, cpf, email, pageable)
            if(listCustomer.isEmpty) ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomGlobalExceptionHandler().customHandleNotFound("Customer Not Found")
            )
            else ResponseEntity.ok(
                ResponseApiDTO(
                    LocalDateTime.now(),
                    HttpStatus.OK.toString(),
                    listCustomer,
                    null
                )
            )
        }catch (ex: Exception){
            ResponseEntity.badRequest().body(
                ex.message?.let { CustomGlobalExceptionHandler().customHandleBadRequest(it) }
            )
        }

    override fun editCustomer(customerId: Long, customer: CustomerSaveDTO): ResponseEntity<Any> {
        try {
            val oldCustomer = customerProvider.searchCustomerById(customerId)
                ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    CustomGlobalExceptionHandler().customHandleNotFound("Customer Not Found")
                )
            customer.customerId = oldCustomer.customerId
            return ResponseEntity.ok(
                ResponseApiDTO(
                    LocalDateTime.now(),
                    HttpStatus.OK.toString(),
                    customerProvider.editCustomer(Customer(customer, oldCustomer.createdDate, LocalDateTime.now())),
                    null
                )
            )
        }catch (ex: Exception){
            return ResponseEntity.badRequest().body(
                ex.message?.let { CustomGlobalExceptionHandler().customHandleBadRequest(it) }
            )
        }
    }

    override fun deleteCustomer(customerId: Long): ResponseEntity<Any> {
        try {
            val customerDelete = customerProvider.searchCustomerById(customerId)
                ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    CustomGlobalExceptionHandler().customHandleNotFound("Customer Not Found")
                )
            return ResponseEntity.ok(
                ResponseApiDTO(
                    LocalDateTime.now(),
                    HttpStatus.OK.toString(),
                    customerProvider.deleteCustomer(customerDelete),
                    null
                )
            )
        }catch (ex: Exception){
            return ResponseEntity.badRequest().body(
                ex.message?.let { CustomGlobalExceptionHandler().customHandleBadRequest(it) }
            )
        }
    }
}