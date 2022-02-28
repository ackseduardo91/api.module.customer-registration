package com.aeon.api.module.customerregistration.modules.customer.controller

import com.aeon.api.module.customerregistration.models.dtos.CustomerDTO
import com.aeon.api.module.customerregistration.models.dtos.CustomerSaveDTO
import com.aeon.api.module.customerregistration.modules.customer.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Validated
@RestController
@RequestMapping("/customer")
//@Api(value = "qualification_analysis", description = "Endpoints relacionados à analise da convocação.")
class CrudCustomerController(
    private val customerService: CustomerService
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = ["application/json"])
//    @ApiOperation(value = "Retorna lista de empresas/editoras que receberam convocação para habilitação.", produces = "application/json")
//    @ApiResponses(value = [
//        ApiResponse(
//            code = 200,
//            message = "Retorna lista de empresas/editoras que receberam convocação para habilitação.",
//            response = SearchCompanyListDTO::class),
//        ApiResponse(code = 401, message = "unauthorized"),
//        ApiResponse(code = 404, message = "edital.object.not.found")
//    ])
    fun searchListCustomerPaginatedWithFilter(
        @RequestParam(name = "name", required = false) name: String?,
        @RequestParam(name = "cpf", required = false) cpf: String?,
        @RequestParam(name = "email", required = false) email: String?,
        @RequestParam(name = "page",defaultValue = "0") page: Int,
        @RequestParam(name = "size",defaultValue = "10") size: Int): Page<CustomerDTO> =
        customerService.searchListCustomerPaginatedWithFilter(name, cpf, email, PageRequest.of(page, size))

    @PostMapping("/new", produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
//    @ApiOperation(value = "Valida o resultado do recaptcha", consumes = "application/json")
//    @ApiResponses(value = [
//        ApiResponse(code = 204, message = "Se o código for valido", response = Any::class),
//        ApiResponse(code = 400, message = "captcha.invalid")
//    ])
    fun saveCustomer(@Valid @RequestBody customer: CustomerSaveDTO): String =
        customerService.saveCustomer(customer)

    @PatchMapping("/edit/customer/{customerId}", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "Valida o resultado do recaptcha", consumes = "application/json")
//    @ApiResponses(value = [
//        ApiResponse(code = 204, message = "Se o código for valido", response = Any::class),
//        ApiResponse(code = 400, message = "captcha.invalid")
//    ])
    fun editCustomer(@PathVariable @NotNull customerId: UUID, @Valid @RequestBody customer: CustomerSaveDTO): String? =
        customerService.editCustomer(customerId, customer)

    @DeleteMapping("/delete/customer/{customerId}", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "Valida o resultado do recaptcha", consumes = "application/json")
//    @ApiResponses(value = [
//        ApiResponse(code = 204, message = "Se o código for valido", response = Any::class),
//        ApiResponse(code = 400, message = "captcha.invalid")
//    ])
    fun deleteCustomer(@PathVariable @NotNull customerId: UUID): String? =
        customerService.deleteCustomer(customerId)
}