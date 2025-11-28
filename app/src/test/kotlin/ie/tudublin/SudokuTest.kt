package ie.tudublin

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SudokuTest {

    @Test
    fun solvesValidPuzzleOne() {
        val puzzle = listOf(
            "850002400",
            "720000009",
            "004000000",
            "000107002",
            "305000900",
            "040000000",
            "000080070",
            "017000000",
            "000036040"
        )
        val sudoku = Sudoku(puzzle)
        assertTrue(sudoku.solve())
    }

    @Test
    fun solvesValidPuzzleTwo() {
        val puzzle = listOf(
            "530070000",
            "600195000",
            "098000060",
            "800060003",
            "400803001",
            "700020006",
            "060000280",
            "000419005",
            "000080079"
        )
        val sudoku = Sudoku(puzzle)
        assertTrue(sudoku.solve())
    }

    @Test
    fun unsolvableBoardFailsAsRequired() {
        val bad = List(9) { "999999999" }
        val sudoku = Sudoku(bad)
        assertFalse(sudoku.solve())
    }
}
