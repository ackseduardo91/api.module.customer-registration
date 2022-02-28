package com.aeon.api.module.customerregistration.database.specifications

import com.aeon.api.module.customerregistration.database.entities.Customer
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class CustomerSpecification {

    fun dynamicSearchFilter(name: String?, cpf: String?, email: String?): Specification<Customer> =
        Specification{ root, query, criteriaBuilder ->
            criteriaBuilder.and(
                searchByName(name, criteriaBuilder, root),
                searchByCpf(cpf, criteriaBuilder, root),
                searchByEmail(email, criteriaBuilder, root)
            )
        }

    private fun searchByName(term: String?, criteriaBuilder: CriteriaBuilder, root: Root<Customer>): Predicate? = term?.let {
        return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%${it}%")
    }

    private fun searchByCpf(term: String?, criteriaBuilder: CriteriaBuilder, root: Root<Customer>): Predicate? = term?.let {
        return  criteriaBuilder.like(criteriaBuilder.lower(root.get("cpf")), "%${it}%")
    }

    private fun searchByEmail(term: String?, criteriaBuilder: CriteriaBuilder, root: Root<Customer>): Predicate? = term?.let {
        return criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%${it}%")
    }
}