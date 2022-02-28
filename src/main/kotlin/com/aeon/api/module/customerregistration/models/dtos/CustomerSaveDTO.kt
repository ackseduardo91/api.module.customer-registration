package com.aeon.api.module.customerregistration.models.dtos

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.util.*
import javax.validation.constraints.NotNull

data class CustomerSaveDTO(
    @JsonProperty("customerId")
    var customerId: UUID? = null,

    @JsonProperty("name")
    @field:NotNull(message = "name.required")
    val name: String,

    @JsonProperty("cpf")
    @field:NotNull(message = "cpf.required")
    val cpf: String,

    @JsonProperty("email")
    @field:NotNull(message = "email.required")
    val email: String,

    @JsonProperty("birthDate")
    @field:NotNull(message = "birthDate.required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val birthDate: LocalDate,
)

