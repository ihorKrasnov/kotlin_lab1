package com.example.mycontacts

data class PhoneNumber(val type: String, val number: String)
data class Contact(val firstName: String, val lastName: String, val phone: PhoneNumber )

val contactsList = mutableListOf<Contact>()

fun main() {
    while (true) {
        handleCommand(readln())
    }
}

fun handleCommand(command: String) {
    when (command.replace(" ", "")) {
        "--help" -> showHelp()
        "rm" -> remove()
        "new" -> createNew()
        "list" -> listItems()
        "upd" -> update()
        else -> println("Unknown command: $command. " +
                "Try use \"--help\" for viewing the list of available commands")
    }
}

fun showHelp() {
    println("Type \"list\" for viewing list of contacts;")
    println("Type \"new\" for creating new contact;")
    println("Type \"rm\" for removing contact;")
    println("Type \"upd\" for updating contact;")
}

fun remove() {
    listItems()
    println("To approve removing contact enter the valid Id: from this list")

    val id = readln().toIntOrNull()
    if (id == null || id !in contactsList.indices) {
        println("Error: Invalid data")
    } else {
        contactsList.removeAt(id)
        println("Successfully removed")
    }
}

fun createNew() {
    println("Name:")
    val name = readln()

    println("LastName:")
    val lastName = readln()

    if (name == "" && lastName == "") {
        println("Name or LastName should be entered!")
        return createNew()
    }

    println("PhoneType(optional):")
    val type = readln().takeIf { it.isNotBlank() } ?: "Mobile"

    println("PhoneNumber:")
    val phoneNumber = readln()

    val contact = Contact(name, lastName, PhoneNumber(type, phoneNumber))
    contactsList.add(contact)

    println("Contact created successfully.")
}

fun update() {
    listItems()
    println("Enter the ID of the contact you want to update:")
    val id = readln().toIntOrNull()

    if (id == null || id !in contactsList.indices) {
        println("Error: Invalid ID")
        return
    }

    println("Enter updated Name (leave empty to keep current: ${contactsList[id].firstName}):")
    val newName = readln().takeIf { it.isNotBlank() } ?: contactsList[id].firstName

    println("Enter updated LastName (leave empty to keep current: ${contactsList[id].lastName}):")
    val newLastName = readln().takeIf { it.isNotBlank() } ?: contactsList[id].lastName

    println("Enter updated Phone Type (leave empty to keep current: ${contactsList[id].phone.type}):")
    val newPhoneType = readln().takeIf { it.isNotBlank() } ?: contactsList[id].phone.type

    println("Enter updated Phone Number (leave empty to keep current: ${contactsList[id].phone.number}):")
    val newPhoneNumber = readln().takeIf { it.isNotBlank() } ?: contactsList[id].phone.number

    val updatedContact = Contact(newName, newLastName, PhoneNumber(newPhoneType, newPhoneNumber))
    contactsList[id] = updatedContact

    println("Contact updated successfully.")
}

fun listItems() {
    if (!contactsList.any()) {
        println("Contact list is empty; Type \"new\" for adding")
    }
    for (contact in contactsList) {
        println("Id: ${contactsList.indexOf(contact)}")
        println("Name: ${contact.firstName}")
        println("LastName: ${contact.lastName}")
        println("PhoneNumbers: ")
        println("${contact.phone.type} \t ${contact.phone.number}")
    }
}
