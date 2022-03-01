package com.aeon.api.module.customerregistration.database.specifications

import com.aeon.api.module.customerregistration.database.entities.Customer
import org.springframework.data.jpa.domain.Specification
import org.springframework.util.StringUtils
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class CustomerSpecification {

    fun dynamicSearchFilter(name: String?, cpf: String?, email: String?): Specification<Customer>? {
        return Specification{ root: Root<Customer>, query: CriteriaQuery<*>?, builder: CriteriaBuilder ->
            val predicateList: MutableList<Predicate> = ArrayList()
            if (StringUtils.hasText(name)) {
                val fieldName = root.get<String>("name")
                val predicateName =
                    builder.like(fieldName, "%${name}%")
                predicateList.add(predicateName)
            }
            if (StringUtils.hasText(cpf)) {
                val fieldCpf = root.get<String>("cpf")
                val predicateCpf = builder.equal(fieldCpf, cpf)
                predicateList.add(predicateCpf)
            }
            if (StringUtils.hasText(email)) {
                val fieldEmail = root.get<String>("email")
                val predicateEmail = builder.equal(fieldEmail, email)
                predicateList.add(predicateEmail)
            }
            builder.and(*predicateList.toTypedArray())
        }
    }
}