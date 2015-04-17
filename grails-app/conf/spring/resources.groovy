import sample.Book
import sample.MyJsonCollectionRenderer
import sample.MyJsonRenderer

// Place your Spring DSL code here
beans = {
    bookRenderer(MyJsonRenderer, Book)
    booksRenderer(MyJsonCollectionRenderer, Book)
}
