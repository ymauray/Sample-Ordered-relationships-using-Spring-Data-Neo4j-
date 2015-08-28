package fr.skym.demo.entity

import org.springframework.data.neo4j.annotation.EndNode
import org.springframework.data.neo4j.annotation.Fetch
import org.springframework.data.neo4j.annotation.GraphId
import org.springframework.data.neo4j.annotation.RelationshipEntity
import org.springframework.data.neo4j.annotation.StartNode

@RelationshipEntity(type = "has_number")
class NumberRel {
    @GraphId Long id
    @StartNode Person person
    @EndNode @Fetch PhoneNumber number
    int sequence
}
