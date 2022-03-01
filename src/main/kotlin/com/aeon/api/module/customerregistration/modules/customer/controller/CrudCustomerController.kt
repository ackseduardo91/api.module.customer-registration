package com.aeon.api.module.customerregistration.modules.customer.controller

import com.aeon.api.module.customerregistration.models.dtos.CustomerSaveDTO
import com.aeon.api.module.customerregistration.models.error.CustomGlobalExceptionHandler
import com.aeon.api.module.customerregistration.modules.customer.service.CustomerService
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotNull

@RestController
@RequestMapping("/api/customer")
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
        @RequestParam(name = "size",defaultValue = "10") size: Int): ResponseEntity<Any> =
            customerService.searchListCustomerPaginatedWithFilter(name, cpf, email, PageRequest.of(page, size))

    @PostMapping("/new", produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
//    @ApiOperation(value = "Valida o resultado do recaptcha", consumes = "application/json")
//    @ApiResponses(value = [
//        ApiResponse(code = 204, message = "Se o código for valido", response = Any::class),
//        ApiResponse(code = 400, message = "captcha.invalid")
//    ])
    fun saveCustomer(@Valid @RequestBody customer: CustomerSaveDTO, bindingResult: BindingResult): ResponseEntity<Any> {
        if (bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                CustomGlobalExceptionHandler().customHandleForbidden(bindingResult.fieldErrors.toString())
            )
        }
        return customerService.saveCustomer(customer)
    }


    @PatchMapping("/{customerId}/edit", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "Valida o resultado do recaptcha", consumes = "application/json")
//    @ApiResponses(value = [
//        ApiResponse(code = 204, message = "Se o código for valido", response = Any::class),
//        ApiResponse(code = 400, message = "captcha.invalid")
//    ])
    fun editCustomer(
        @PathVariable @NotNull customerId: Long,
        @Valid @RequestBody customer: CustomerSaveDTO,
        bindingResult: BindingResult): ResponseEntity<Any> {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                CustomGlobalExceptionHandler().customHandleForbidden(bindingResult.fieldErrors.toString())
            )
        }
        return customerService.editCustomer(customerId, customer)
    }

    @DeleteMapping("/{customerId}/delete", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "Valida o resultado do recaptcha", consumes = "application/json")
//    @ApiResponses(value = [
//        ApiResponse(code = 204, message = "Se o código for valido", response = Any::class),
//        ApiResponse(code = 400, message = "captcha.invalid")
//    ])
    fun deleteCustomer(@PathVariable @NotNull customerId: Long): ResponseEntity<Any> =
        customerService.deleteCustomer(customerId)
}