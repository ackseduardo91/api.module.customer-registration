package com.aeon.api.module.customerregistration.database.entities

import com.aeon.api.module.customerregistration.models.dtos.CustomerSaveDTO
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "customer")
data class Customer (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    var customerId: Long? = null,

    @Column(name = "name")
    val name: String,

    @Column(name = "cpf")
    val cpf: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "birth_date")
    val birthDate: LocalDate,
){

    @Column(name = "created_date")
    var createdDate: LocalDateTime? = null

    @Column(name = "updated_date")
    var updatedDate: LocalDateTime? = null

    constructor(clientSaveDto: CustomerSaveDTO,
                createdDate: LocalDateTime?,
                updatedDate: LocalDateTime?): this(
        customerId = clientSaveDto.customerId,
        name = clientSaveDto.name,
        cpf = clientSaveDto.cpf,
        email = clientSaveDto.email,
        birthDate = clientSaveDto.birthDate,
    ){
        this.createdDate = createdDate
        this.updatedDate = updatedDate
    }
}