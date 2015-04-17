import grails.converters.JSON
import sample.Book

class BootStrap {

    def init = { servletContext ->
        JSON.createNamedConfig('short') {
            it.registerObjectMarshaller(Book) { Book book, JSON converter ->
                converter.build {
                    id book.id
                    title book.title
                }
            }
        }
        JSON.createNamedConfig('full') {
            it.registerObjectMarshaller(Book) { Book book, JSON converter ->
                converter.build {
                    id book.id
                    title book.title
                    isbn book.isbn
                }
            }
        }

        10.times {
            new Book(title: "test book ${it}", isbn: "isbn${it}").save()
        }
    }
    def destroy = {
    }
}
