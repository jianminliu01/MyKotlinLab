package mine.blog.kotlin.lab.data

data class StockDailyQuote(val symbol: String, val date: String, val open: Double, val high: Double, val low: Double, val close: Double, val adjClose: Double?, val volume: Int) {
    fun getMonth(): String = date.replace("/\\d+/".toRegex(), "/")

    fun getYear(): String = date.replace("/\\d+/\\d+".toRegex(), "")
}