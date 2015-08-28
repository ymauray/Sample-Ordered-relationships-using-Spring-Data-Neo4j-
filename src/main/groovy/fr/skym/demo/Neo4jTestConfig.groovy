package fr.skym.demo

import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.factory.GraphDatabaseFactory
import org.neo4j.shell.ShellSettings
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.neo4j.config.Neo4jConfiguration

@Configuration
class Neo4jTestConfig extends Neo4jConfiguration {

    Neo4jTestConfig() {
        setBasePackage("fr.skym.demo")
    }

    @Bean
    GraphDatabaseService graphDatabaseService(@Value('${neo4j.shell.port}') String port) {
        return new GraphDatabaseFactory()
                .newEmbeddedDatabaseBuilder("demo.neo4j.db")
                .setConfig(ShellSettings.remote_shell_enabled, "true")
                .setConfig(ShellSettings.remote_shell_port, port)
                .newGraphDatabase()
    }
}
