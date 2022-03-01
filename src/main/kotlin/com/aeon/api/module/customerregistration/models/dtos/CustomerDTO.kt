package com.aeon.api.module.customerregistration.models.dtos

import com.aeon.api.module.customerregistration.database.entities.Customer
import com.aeon.api.module.customerregistration.models.utils.DataUtils
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.br.CPF
import java.io.Serializable
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CustomerDTO(
    @JsonProperty("customerId")
    val customerId: Long? = null,

    @JsonProperty("name")
    @field:NotNull(message = "name.required")
    @field:NotBlank(message = "name.required")
    val name: String,

    @JsonProperty("cpf")
    @field:NotNull(message = "cpf.required")
    @field:NotBlank(message = "cpf.required")
    @field:CPF(message = "cpf.invalid")
    val cpf: String,

    @JsonProperty("email")
    @field:NotNull(message = "email.required")
    @field:NotBlank(message = "email.required")
    @field:Email(message = "email.invalid")
    val email: String,

    @JsonProperty("age")
    @field:Max(999)
    @field:NotNull(message = "age.required")
    @field:NotBlank(message = "age.required")
    val age: Int

) : Serializable {

    constructor(customer: Customer): this(
        customerId = customer.customerId,
        name = customer.name,
        cpf = customer.cpf,
        email = customer.email,
        age = DataUtils().getCustomerAge(customer.birthDate)
    )
}

