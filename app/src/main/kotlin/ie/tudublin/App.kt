package ie.tudublin

import java.io.File

fun main(args: Array<String>) {

    if (args.isEmpty()) {
        println("Usage: ./run/app/bin/app puzzles/board.txt")
        return
    }

    val file = File(args[0])

    if (!file.exists()) {
        println("Error: File not found -> ${args[0]}")
        return
    }

    val lines = file.readLines()
    require(lines.size == 9) { "Grid must be 9 rows" }

    // Convert each row to integers
    val board = Array(9) { r ->
        val nums = lines[r].trim().split(" ")
        require(nums.size == 9) { "Each row must contain 9 values" }
        IntArray(9) { c -> nums[c].toInt() }
    }

    val sudoku = Sudoku(board)

    println("\nINPUT BOARD:\n")
    board.forEach { println(it.joinToString(" ")) }

    if (sudoku.solve()) {
        println("\nSOLVED BOARD:\n")
        sudoku.getBoard().forEach { println(it.joinToString(" ")) }
    } else {
        println("\nNo solution found (invalid or too complex)")
    }
}
