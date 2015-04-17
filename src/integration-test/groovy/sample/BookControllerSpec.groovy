package sample

import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.TestRestTemplate
import org.springframework.http.HttpStatus
import spock.lang.Specification

@Integration
@Rollback
class BookControllerSpec extends Specification {

    @Value('${local.server.port}')
    int port

    TestRestTemplate template = new TestRestTemplate()

    def "GET /books/{id}"() {
        given:
            def book = Book.withNewTransaction { new Book(title: 'test title', isbn: '123').save() }

        when:
            def entity = template.getForEntity("${baseUrl}/v2/books/${book.id}", Map)

        then:
            entity.statusCode == HttpStatus.OK
            entity.body.title == 'test title'

        cleanup:
            Book.withNewTransaction { book.delete() }
    }

    def "POST /books"() {
        when:
            def entity = template.postForEntity("${baseUrl}/v2/books", [title: 'test title', isbn: '123'], Map)

        then:
            entity.statusCode == HttpStatus.CREATED
            entity.body.title == 'test title'

        cleanup:
            Book.withNewTransaction { Book.get(entity.body.id as Long).delete() }
    }

    private String getBaseUrl() {
        "http://localhost:${port}"
    }
}
