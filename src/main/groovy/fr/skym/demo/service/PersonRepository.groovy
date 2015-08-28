package fr.skym.demo.service

import fr.skym.demo.entity.Person
import org.springframework.data.neo4j.repository.CypherDslRepository
import org.springframework.data.neo4j.repository.GraphRepository

interface PersonRepository extends GraphRepository<Person>, CypherDslRepository<Person> {
    Person findByName(String name)
}
