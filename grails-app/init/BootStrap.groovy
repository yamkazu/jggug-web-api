import sample.Book

class BootStrap {

    def init = { servletContext ->
        10.times {
            new Book(title: "test book ${it}").save()
        }
    }
    def destroy = {
    }
}
