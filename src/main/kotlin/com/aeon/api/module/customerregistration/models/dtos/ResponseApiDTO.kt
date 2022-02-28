package com.aeon.api.module.customerregistration.models.dtos

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ResponseApiDTO<T>(
    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    val timestamp: LocalDateTime? = null,

    @JsonProperty("status")
    val status: String? = null,

    @JsonProperty("error")
    val error: String? = null,

    @JsonProperty("data")
    val data: T? = null,

    @JsonProperty("errorList")
    val errorList: List<String>? = null,
)
