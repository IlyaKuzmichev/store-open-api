package edu.school21.store.repository.entity

import jakarta.persistence.*

@Entity
data class Supplier(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(nullable = false)
    var name: String,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(referencedColumnName = "id")
    var address: Address,

    var phoneNumber: String,
)
