package com.aeon.api.module.customerregistration.models.dtos

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ResponseApiDTO(
    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    val timestamp: LocalDateTime,

    @JsonProperty("status")
    val status: String,

    @JsonProperty("data")
    val data: Any?,

    @JsonProperty("error")
    val error: String? = null,
)
