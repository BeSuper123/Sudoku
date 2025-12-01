package ie.tudublin

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SudokuTest {

    private fun makeBoard(lines: List<String>): Array<IntArray> {
        return Array(9) { r ->
            val nums = lines[r].split(" ").map { it.toInt() }
            IntArray(9) { c -> nums[c] }
        }
    }

    @Test
    fun testValidPuzzleSolves() {
        val puzzle = listOf(
            "5 3 0 0 7 0 0 0 0",
            "6 0 0 1 9 5 0 0 0",
            "0 9 8 0 0 0 0 6 0",
            "8 0 0 0 6 0 0 0 3",
            "4 0 0 8 0 3 0 0 1",
            "7 0 0 0 2 0 0 0 6",
            "0 6 0 0 0 0 2 8 0",
            "0 0 0 4 1 9 0 0 5",
            "0 0 0 0 8 0 0 7 9"
        )

        val sudoku = Sudoku(makeBoard(puzzle))
        assertTrue(sudoku.solve())
    }

    @Test
    fun testAlreadySolvedPuzzle() {
        val puzzle = listOf(
            "5 3 4 6 7 8 9 1 2",
            "6 7 2 1 9 5 3 4 8",
            "1 9 8 3 4 2 5 6 7",
            "8 5 9 7 6 1 4 2 3",
            "4 2 6 8 5 3 7 9 1",
            "7 1 3 9 2 4 8 5 6",
            "9 6 1 5 3 7 2 8 4",
            "2 8 7 4 1 9 6 3 5",
            "3 4 5 2 8 6 1 7 9"
        )

        val sudoku = Sudoku(makeBoard(puzzle))
        assertTrue(sudoku.solve())
    }

    @Test
    fun testInvalidPuzzleFails() {
        val puzzle = listOf(
            "1 1 1 1 1 1 1 1 1",
            "1 1 1 1 1 1 1 1 1",
            "1 1 1 1 1 1 1 1 1",
            "1 1 1 1 1 1 1 1 1",
            "1 1 1 1 1 1 1 1 1",
            "1 1 1 1 1 1 1 1 1",
            "1 1 1 1 1 1 1 1 1",
            "1 1 1 1 1 1 1 1 1",
            "1 1 1 1 1 1 1 1 1"
        )

        val sudoku = Sudoku(makeBoard(puzzle))
        assertFalse(sudoku.solve())
    }
}
