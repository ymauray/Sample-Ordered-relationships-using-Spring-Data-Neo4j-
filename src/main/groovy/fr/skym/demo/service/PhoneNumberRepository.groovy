package fr.skym.demo.service

import fr.skym.demo.entity.PhoneNumber
import org.springframework.data.neo4j.repository.CypherDslRepository
import org.springframework.data.neo4j.repository.GraphRepository

interface PhoneNumberRepository extends GraphRepository<PhoneNumber>, CypherDslRepository<PhoneNumber> {
    /* Empty */
}
