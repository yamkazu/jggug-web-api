package sample

import grails.rest.RestfulController

class BookController extends RestfulController<Book> {

    static List<String> responseFormats = ['json', 'xml']

    BookController() {
        super(Book)
    }

    @Override
    def show() {
        respond queryForResource(params.id), includes: includeFields
    }

    private List<String> getIncludeFields() {
        params.fields?.split(',') as List<String> ?: Collections.emptyList()
    }
}
