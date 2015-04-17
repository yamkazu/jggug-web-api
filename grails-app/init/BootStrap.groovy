import grails.converters.JSON
import sample.Book

class BootStrap {

    def init = { servletContext ->
        JSON.registerObjectMarshaller(Book) { Book book, JSON converter ->
            converter.build {
                id book.id
                title book.title
            }
        }

        10.times {
            new Book(title: "test book ${it}").save()
        }
    }
    def destroy = {
    }
}
