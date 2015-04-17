package sample.v1

import grails.rest.RestfulController
import sample.Book

class BookController extends RestfulController<Book> {

    static String namespace = 'v1'

    static List<String> responseFormats = ['json', 'xml']

    BookController() {
        super(Book)
    }
}
