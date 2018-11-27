package mine.blog.kotlin.lab.service

import mine.blog.kotlin.lab.data.StockDailyQuote
import org.springframework.stereotype.Service

@Service
class StockDailyQuoteService {
    fun <R, T:Comparable<T>> query(quotes: List<StockDailyQuote>, groupCiteria: (StockDailyQuote) -> R, selectCriteria:
    (StockDailyQuote) -> T): Map<R, StockDailyQuote?> {
        return quotes.groupBy(groupCiteria).mapValues { it.value.maxBy(selectCriteria)}
    }

    fun readQuotes(fileName: String): List<StockDailyQuote> {
        val quotes = mutableListOf<StockDailyQuote>()

        fun toStockDailyQuote(line: String): StockDailyQuote {
            var p = line.split(",").map { it }
            return StockDailyQuote(p[0], p[1], p[2].toDouble(), p[3].toDouble(), p[4].toDouble(), p[5].toDouble(),
                    p[6].toDoubleOrNull(), p[7].toInt())
        }

        StockDailyQuoteService::class.java.classLoader.getResource(fileName).openStream().bufferedReader().useLines { lines
            ->
            lines
                    .forEachIndexed {index, value ->
                        if (index > 0) {
                            quotes.add(toStockDailyQuote(value))
                        }

                    }
        }
        return quotes
    }
}