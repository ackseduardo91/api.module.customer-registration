package com.aeon.api.module.customerregistration.models.dtos

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


data class CustomerSaveDTO(
    @JsonProperty("customerId")
    var customerId: Long? = null,

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

    @JsonProperty("birthDate")
    @field:NotNull(message = "birthDate.required")
    @field:NotBlank(message = "birthDate.required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val birthDate: LocalDate,
)

