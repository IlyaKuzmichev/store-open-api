package edu.school21.store.repository.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Client(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(nullable = false)
    var clientName: String,

    @Column(nullable = false)
    var clientSurname: String,

    @Column(nullable = false)
    var birthday: LocalDate,

    @Column(nullable = false)
    var gender: Char,

    @Column(nullable = false)
    var registrationDate: LocalDate? = null,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(referencedColumnName = "id")
    var address: Address,
) {
    @PrePersist
    fun prePersist() {
        if (registrationDate == null) {
            registrationDate = LocalDate.now()
        }
    }
}
