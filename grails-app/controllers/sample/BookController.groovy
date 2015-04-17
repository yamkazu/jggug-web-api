package sample

import grails.rest.RestfulController

class BookController extends RestfulController<Book> {

    static List<String> responseFormats = ['json', 'xml']

    BookController() {
        super(Book)
    }
}
