package ie.tudublin

class Sudoku(rows: List<String>) {

    private val grid = IntArray(81)
    private var solved = false

    private var iterations = 0
    private val maxIterations = 2_000_000

    init {
        require(rows.size == 9 && rows.all { it.length == 9 }) { "Grid must be 9x9" }
        for (i in 0..8)
            for (j in 0..8)
                grid[9 * i + j] = rows[i][j].digitToInt()
    }

    fun solve(): Boolean {
        if (!initialBoardValid()) return false
        placeNumber(0)
        return solved
    }

    fun getBoard(): List<List<Int>> =
        (0 until 9).map { r -> (0 until 9).map { c -> grid[r*9 + c] } }

    private fun placeNumber(pos: Int) {
        if (solved || iterations++ > maxIterations) return
        if (pos == 81) { solved = true; return }

        if (grid[pos] > 0) return placeNumber(pos + 1)

        for (n in 1..9) {
            if (checkValidity(n, pos % 9, pos / 9)) {
                grid[pos] = n
                placeNumber(pos + 1)
                if (solved) return
                grid[pos] = 0
            }
        }
    }

    private fun checkValidity(v: Int, x: Int, y: Int): Boolean {
        for (i in 0..8)
            if (grid[y*9+i] == v || grid[i*9+x] == v) return false

        val boxX = x/3*3
        val boxY = y/3*3

        for (i in boxY until boxY + 3)
            for (j in boxX until boxX + 3)
                if (grid[i*9+j] == v) return false

        return true
    }

    private fun initialBoardValid(): Boolean {
        for (y in 0 until 9)
            for (x in 0 until 9) {
                val v = grid[y*9+x]
                if (v != 0) {
                    grid[y*9+x] = 0
                    if (!checkValidity(v, x, y)) return false
                    grid[y*9+x] = v
                }
            }
        return true
    }
}
