package com.aeon.api.module.customerregistration.models.dtos

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import javax.validation.constraints.NotNull

data class CustomerSaveDTO(
    @JsonProperty("customerId")
    var customerId: Long? = null,

    @JsonProperty("name")
    @NotNull(message = "name.required")
    val name: String,

    @JsonProperty("cpf")
    @NotNull(message = "cpf.required")
    val cpf: String,

    @JsonProperty("email")
    @NotNull(message = "email.required")
    val email: String,

    @JsonProperty("birthDate")
    @NotNull(message = "birthDate.required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val birthDate: LocalDate,
)

