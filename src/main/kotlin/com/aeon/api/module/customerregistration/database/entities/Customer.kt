package com.aeon.api.module.customerregistration.database.entities

import com.aeon.api.module.customerregistration.models.dtos.CustomerSaveDTO
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "client")
data class Customer (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id", nullable = false)
    val customerId: UUID? = null,

    @Column(name = "name")
    val name: String,

    @Column(name = "cpf")
    val cpf: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "birth_date")
    val birthDate: LocalDate,
){

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "updated_date")
    lateinit var updatedDate: LocalDateTime

    constructor(clientSaveDto: CustomerSaveDTO): this(
        customerId = clientSaveDto.customerId,
        name = clientSaveDto.name,
        cpf = clientSaveDto.cpf,
        email = clientSaveDto.email,
        birthDate = clientSaveDto.birthDate,
    )

}