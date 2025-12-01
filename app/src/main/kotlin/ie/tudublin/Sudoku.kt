package ie.tudublin

class Sudoku(private val board: Array<IntArray>) {

    private val maxIterations = 2_000_000
    private var iterations = 0

    fun solve(): Boolean {
        iterations = 0

        // Ensure puzzle is valid BEFORE solving
        if (!isValidBoard()) return false

        return solveRecursive()
    }

    fun getBoard(): Array<IntArray> = board


    private fun solveRecursive(): Boolean {
        iterations++
        if (iterations > maxIterations) return false   // prevents infinite searching

        val empty = findEmptyCell() ?: return true     // solved!
        val (row, col) = empty

        for (num in 1..9) {
            if (isSafe(row, col, num)) {
                board[row][col] = num
                if (solveRecursive()) return true
                board[row][col] = 0 // backtrack
            }
        }
        return false
    }


    // Find first empty cell
    private fun findEmptyCell(): Pair<Int, Int>? {
        for (r in 0 until 9)
            for (c in 0 until 9)
                if (board[r][c] == 0) return Pair(r, c)
        return null
    }

    // Check if number can be placed
    private fun isSafe(row: Int, col: Int, num: Int): Boolean {

        // Check row
        for (c in 0 until 9)
            if (board[row][c] == num) return false

        // Check column
        for (r in 0 until 9)
            if (board[r][col] == num) return false

        // Check 3x3 square
        val boxRow = row - row % 3
        val boxCol = col - col % 3
        for (r in boxRow until boxRow + 3)
            for (c in boxCol until boxCol + 3)
                if (board[r][c] == num) return false

        return true
    }

    // Validate board BEFORE solving
    private fun isValidBoard(): Boolean {

        // Validate rows & columns
        for (i in 0 until 9) {
            val rowSet = mutableSetOf<Int>()
            val colSet = mutableSetOf<Int>()

            for (j in 0 until 9) {
                val r = board[i][j]
                val c = board[j][i]

                if (r != 0 && !rowSet.add(r)) return false
                if (c != 0 && !colSet.add(c)) return false
            }
        }

        // Validate 3x3 subgrids
        for (boxR in 0 until 9 step 3)
            for (boxC in 0 until 9 step 3) {
                val set = mutableSetOf<Int>()
                for (r in boxR until boxR + 3)
                    for (c in boxC until boxC + 3) {
                        val num = board[r][c]
                        if (num != 0 && !set.add(num)) return false
                    }
            }

        return true
    }
}
