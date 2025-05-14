package edu.school21.store.repository.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var category: String,

    @Column(nullable = false)
    var price: BigDecimal,

    @Column(nullable = false)
    var availableStock: Int = 0,

    @Column(nullable = false)
    var lastUpdateDate: LocalDate?,

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    var supplier: Supplier,

    @ManyToOne
    @JoinColumn(name = "image_id")
    var image: Image? = null,
) {
    @PrePersist
    fun prePersist() {
        if (lastUpdateDate == null) {
            lastUpdateDate = LocalDate.now()
        }
    }
}
