package sample

import grails.rest.Resource

@Resource(uri = '/books', formats = ['json', 'xml'])
class Book {
    String title
}
