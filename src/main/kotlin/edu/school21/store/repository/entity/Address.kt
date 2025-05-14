package edu.school21.store.repository.entity

import jakarta.persistence.*

@Entity
data class Address(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var country: String,

    @Column(nullable = false)
    var city: String,

    @Column
    var street: String,
)
