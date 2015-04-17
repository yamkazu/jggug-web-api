import grails.converters.JSON
import sample.Book

class BootStrap {

    def init = { servletContext ->
        JSON.registerObjectMarshaller(Book) { Book book, JSON converter ->
            def includes = converter.getIncludes(Book) ?: ['id', 'title']
            converter.build {
                includes.each {
                    "$it" book."$it"
                }
            }
        }

        10.times {
            new Book(title: "test book ${it}").save()
        }
    }
    def destroy = {
    }
}
