import grails.rest.render.json.JsonRenderer
import sample.Book

// Place your Spring DSL code here
beans = {
    bookRenderer(JsonRenderer, Book) {
        includes = ['title']
    }
}
