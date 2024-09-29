package com.example.mycontacts

class LibraryService {
    private val context = mutableListOf<Book>()

    fun getFormatBookInfoMessage(book: Book): String {
        return """
        Id: ${context.indexOf(book)}
        Name: ${book.name}
        Author: ${book.author}
        Year of publishing: ${book.publishYear}
        """.trimIndent()
    }

    fun list(): List<Book> {
        return context
    }

    fun addNew(name: String, author: String, year: Int){
        context.add(Book(name, author, year))
    }

    fun search(keyword: String): List<Book> {
        return context.filter { it.name.contains(keyword, ignoreCase = true) ||
                it.author.contains(keyword, ignoreCase = true) }
    }
}
