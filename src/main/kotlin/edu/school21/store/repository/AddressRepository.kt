package edu.school21.store.repository

import edu.school21.store.repository.entity.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AddressRepository : JpaRepository<Address, Long> {

    fun findByCountryAndCityAndStreet(country: String, city: String, street: String): Optional<Address>

    @Query(
        """
    SELECT COUNT(*) 
    FROM (
        SELECT address_id FROM client WHERE address_id = :addressId
        UNION ALL
        SELECT address_id FROM supplier WHERE address_id = :addressId
    ) AS related_records
        """, nativeQuery = true
    )
    fun countAddressUsage(@Param("addressId") addressId: Long): Int
}