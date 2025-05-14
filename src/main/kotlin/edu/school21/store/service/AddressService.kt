package edu.school21.store.service

import edu.school21.store.repository.AddressRepository
import edu.school21.store.repository.entity.Address
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AddressService(private val addressRepository: AddressRepository) {

    @Transactional
    fun getOrCreateAddress(country: String, city: String, street: String): Address {
        return addressRepository.findByCountryAndCityAndStreet(country, city, street)
            .orElseGet {
                val newAddress = Address(country = country, city = city, street = street)
                addressRepository.save(newAddress)
            }
    }

    @Transactional
    fun removeUnusedAddress(address: Address) {
        if (addressRepository.countAddressUsage(address.id ?: 0) == 0) {
            addressRepository.delete(address)
        }
    }
}