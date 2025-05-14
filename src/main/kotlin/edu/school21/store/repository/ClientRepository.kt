package edu.school21.store.repository

import edu.school21.store.repository.entity.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<Client, Long> {
    fun findByClientNameAndClientSurname(clientName: String, clientSurname: String): List<Client>
}