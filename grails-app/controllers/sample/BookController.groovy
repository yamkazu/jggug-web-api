package sample

import grails.rest.RestfulController

class BookController extends RestfulController<Book> {

    static List<String> responseFormats = ['json', 'xml']

    BookController() {
        super(Book)
    }

    @Override
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def paging = [
            'total-count'   : countResources(),
            'current-max'   : params.max,
            'current-offset': params.int('offset', 0)
        ]
        respond listAllResources(params), detail: detail, paging: paging
    }

    @Override
    def show() {
        respond queryForResource(params.id), detail: detail
    }

    private String getDetail() {
        params.detail
    }
}
