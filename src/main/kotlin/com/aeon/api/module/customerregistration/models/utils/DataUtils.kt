package com.aeon.api.module.customerregistration.models.utils

import java.time.LocalDate
import java.time.Period

class DataUtils {

    fun getCustomerAge(birthDate: LocalDate): Int =
        Period.between(birthDate, LocalDate.now()).years
}