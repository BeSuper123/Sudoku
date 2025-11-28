package ie.tudublin

import java.io.File

fun main(args: Array<String>) {

    if (args.isEmpty()) {
        println("Usage: ./gradlew run --args=\"puzzle.txt\"")
        return
    }

    val rows = File(args[0]).readLines()
    val sudoku = Sudoku(rows)

    println("\nInput Board:\n")
    rows.forEach { println(it) }

    if (sudoku.solve()) {
        println("\nSolved Board:\n")
        sudoku.getBoard().forEach { println(it.joinToString(" ")) }
    } else {
        println("\nCould not solve puzzle (invalid or too complex).")
    }
}
