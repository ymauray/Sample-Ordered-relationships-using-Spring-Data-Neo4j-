package fr.skym.demo

import fr.skym.demo.entity.NumberRel
import fr.skym.demo.entity.Person
import fr.skym.demo.entity.PhoneNumber
import fr.skym.demo.service.PersonRepository
import fr.skym.demo.service.PhoneNumberRepository
import groovy.util.logging.Log4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.neo4j.config.EnableNeo4jRepositories
import org.springframework.data.neo4j.support.Neo4jTemplate
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.annotation.Transactional

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@EnableNeo4jRepositories(basePackages =  "fr.skym.demo")
@Log4j
class Application implements CommandLineRunner {

    static void main(String...args) {
        def dir = new File('demo.neo4j.db')
        dir.deleteDir();
        SpringApplication.run Application, args
    }

    @Autowired
    PersonRepository personRepository

    @Autowired
    PhoneNumberRepository phoneNumberRepository

    @Autowired
    Neo4jTemplate template

    @Override
    @Transactional
    void run(String... args) throws Exception {
        log.debug "Hello, SpringApplication !"

        def num01 = new PhoneNumber(number: "0101")
        phoneNumberRepository.save(num01)

        def num02 = new PhoneNumber(number: "0202")
        phoneNumberRepository.save(num02)

        def alice = new Person(
                name: "Alice"
        )
        personRepository.save(alice)

        def r = template.createRelationshipBetween(alice, num01, NumberRel, "has_number", false)
        r.setProperty("sequence", 10)
        template.save(r)
        r = template.createRelationshipBetween(alice, num02, NumberRel, "has_number", false)
        r.setProperty("sequence", 20)
        template.save(r)

        (1..3).each {
            println "Looking for Alice, pass ${it}"
            def p = personRepository.findByName("Alice")
            p.numbers.each { rel ->
                println "${rel.sequence} : ${rel.number?.number}"
            }
            println "====="
        }

    }
}
