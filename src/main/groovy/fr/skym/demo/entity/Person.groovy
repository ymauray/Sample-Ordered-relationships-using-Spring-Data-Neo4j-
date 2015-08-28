package fr.skym.demo.entity

import org.springframework.data.neo4j.annotation.Fetch
import org.springframework.data.neo4j.annotation.GraphId
import org.springframework.data.neo4j.annotation.NodeEntity
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.annotation.RelatedToVia

@NodeEntity
class Person {
    @GraphId Long id
    String name
    @Query(value = "START p = NODE({self}) MATCH p-[r:has_number]->n RETURN r ORDER BY r.sequence ASC")
    @Fetch
    Iterable<NumberRel> numbers
}
