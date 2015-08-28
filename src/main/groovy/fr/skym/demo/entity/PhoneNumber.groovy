package fr.skym.demo.entity

import org.springframework.data.neo4j.annotation.GraphId
import org.springframework.data.neo4j.annotation.NodeEntity

@NodeEntity
class PhoneNumber {
    @GraphId Long id
    String number
}
