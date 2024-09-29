package com.example.mycontacts

val service: LibraryService = LibraryService();

fun main() {
    while (true) {
        handleCommand(readln())
    }
}

fun handleCommand(command: String) {
    when (command.replace(" ", "")) {
        "--help" -> showHelp()
        "new" -> createNew()
        "list" -> listItems()
        "search" -> search()
        else -> println("Unknown command: $command. " +
                "Try use \"--help\" for viewing the list of available commands")
    }
}

fun showHelp() {
    println("Type \"list\" for viewing list of contacts;")
    println("Type \"new\" for creating new contact;")
    println("Type \"search\" for searching by keyword;")
}

fun createNew() {
    println("Name:")
    val name = readln()

    println("Author:")
    val lastName = readln()

    println("Year of publishing:")
    val type = readln()

    service.addNew(name, lastName, type.toInt());
}

fun listItems() {
    for (book in service.list()){
        println(service.getFormatBookInfoMessage(book));
    }
}

fun search() {
    println("Enter the search keyword (Name of book or author)")
    val books = service.search(readln());
    if (books.isEmpty()) {
        println("The result not found!");
        return;
    }
    for (book in books) {
        println(service.getFormatBookInfoMessage(book));
    }
}
