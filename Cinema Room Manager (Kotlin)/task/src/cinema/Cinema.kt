package cinema

import kotlin.compareTo
import kotlin.div
import kotlin.text.compareTo
import kotlin.times

fun readln(prompt: String): String { println(prompt); return readln() }

class Cinema {
    private var rows: Int
    private var seats: Int
    private val busySeats = mutableListOf<Pair<Int, Int>>()

    init {
        while (true) {
            try {
                println("Enter the number of rows:")
                rows = readln().toInt()
                if (rows <= 0) {
                    println("The number of rows must be greater than 0.")
                    continue
                }
                break
            } catch (e: NumberFormatException) {
                println("Invalid input! Please enter a valid number.")
            }
        }

        while (true) {
            try {
                println("Enter the number of seats in each row:")
                seats = readln().toInt()
                if (seats <= 0) {
                    println("The number of seats must be greater than 0.")
                    continue
                }
                break
            } catch (e: NumberFormatException) {
                println("Invalid input! Please enter a valid number.")
            }
        }

    }

    fun showSeats() {
        println("    Cinema:")
        println("  " +  (1..seats).joinToString(" "))
        for (i in 1..rows) {
            print("$i ")
            for (j in 1..seats){
                if (busySeats.contains(Pair(i, j))) print("B ") else print("S ")
            }
            print("\n")
        }
    }

    fun buyTicket() {
        var row: Int
        var seat: Int

        while (true) {
            try {
                row = readln("Enter a row number:").toInt()
                seat = readln("Enter a seat number in that row:").toInt()

                if (row !in 1..rows || seat !in 1..seats) {
                    println("Wrong input!")
                    continue
                }

                if (busySeats.contains(Pair(row, seat))) {
                    println("That ticket has already been purchased!")
                    continue
                }

                break
            } catch (e: NumberFormatException) {
                println("Invalid input!")
            }
        }


        val price = if (rows * seats <= 60) {
            10
        } else {
            val frontRows = rows / 2
            if  (row <= frontRows) 10
            else 8
        }
        busySeats.add(Pair(row, seat))
        println("Ticket price: $$price")
    }

    fun statistics() {
        val totalSeats = rows * seats
        val purchasedTickets = busySeats.size
        var currentIncome = 0

        for (seat in busySeats) {
            currentIncome += if (rows * seats <= 60) {
                10
            } else {
                if (seat.first <= rows / 2) 10 else 8
            }
        }

        val totalIncome = if (totalSeats <= 60) totalSeats * 10 else (rows / 2) * seats * 10 + (rows - rows / 2) * seats * 8

        println("Number of purchased tickets: $purchasedTickets")
        println("Percentage: ${"%.2f".format(purchasedTickets.toDouble() / totalSeats * 100)}%")
        println("Current income: $$currentIncome")
        println("Total income: $$totalIncome")
    }
}

fun main() {
    val cinema = Cinema()

    while (true) {
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        when (readln("Choose an action:").toInt()) {
            1 -> cinema.showSeats()
            2 -> cinema.buyTicket()
            3 -> cinema.statistics()
            0 -> return
        }
    }
}