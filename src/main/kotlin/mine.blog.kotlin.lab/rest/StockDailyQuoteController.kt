package mine.blog.kotlin.lab.rest

import mine.blog.kotlin.lab.data.StockDailyQuote
import mine.blog.kotlin.lab.service.StockDailyQuoteService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/controller/stock")
class StockDailyQuoteController (private val service: StockDailyQuoteService) {
    private val fileName: String = "Quote.csv"

    @GetMapping(path = ["/groupby/{groupby}/select/{select}"],
            produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    @ResponseBody
    fun getMaxSelectAndGroupBy(@PathVariable(name="groupby") groupBy: GroupBy, @PathVariable(name="select") select:
    Select): Map<Pair<String, String>,
            StockDailyQuote?> {
        val quotes: List<StockDailyQuote> = service.readQuotes(fileName)
        //TODO: Best way to reduce the following big when?
        val result: Map<Pair<String, String>, StockDailyQuote?>
        when {
            select == Select.CLOSE && groupBy == GroupBy.MONTH
            -> result = service.query(quotes, { q -> Pair(q.symbol, q.getMonth())}, StockDailyQuote::close)
            select == Select.OPEN && groupBy == GroupBy.MONTH
            -> result = service.query(quotes, { q -> Pair(q.symbol, q.getMonth())}, StockDailyQuote::open)
            select == Select.HIGH && groupBy == GroupBy.MONTH
            -> result = service.query(quotes, { q -> Pair(q.symbol, q.getMonth())}, StockDailyQuote::high)
            select == Select.LOW && groupBy == GroupBy.MONTH
            -> result = service.query(quotes, { q -> Pair(q.symbol, q.getMonth())}, StockDailyQuote::low)
            select == Select.CLOSE_OPEN && groupBy == GroupBy.MONTH
            -> result = service.query(quotes, { q -> Pair(q.symbol, q.getMonth())}, { q -> q.close -q.open})
            select == Select.HIGH_LOW && groupBy == GroupBy.MONTH
            -> result = service.query(quotes, { q -> Pair(q.symbol, q.getMonth())}, { q -> q.high -q.low})
            select == Select.CLOSE && groupBy == GroupBy.YEAR
            -> result = service.query(quotes, { q -> Pair(q.symbol, q.getYear())}, StockDailyQuote::close)
            select == Select.OPEN && groupBy == GroupBy.YEAR
            -> result = service.query(quotes, { q -> Pair(q.symbol, q.getYear())}, StockDailyQuote::open)
            select == Select.HIGH && groupBy == GroupBy.YEAR
            -> result = service.query(quotes, { q -> Pair(q.symbol, q.getYear())}, StockDailyQuote::high)
            select == Select.LOW && groupBy == GroupBy.YEAR
            -> result = service.query(quotes, { q -> Pair(q.symbol, q.getYear())}, StockDailyQuote::low)
            select == Select.CLOSE_OPEN && groupBy == GroupBy.YEAR
            -> result = service.query(quotes, { q -> Pair(q.symbol, q.getYear())}, { q -> q.close -q.open})
            select == Select.HIGH_LOW && groupBy == GroupBy.YEAR
            -> result = service.query(quotes, { q -> Pair(q.symbol, q.getYear())}, { q -> q.high -q.low})
            else -> {
                result = (throw RuntimeException("Operation not supported yet"))
            }
        }
        return result
    }
}

enum class GroupBy {
    MONTH, YEAR
}

enum class Select {
    OPEN, CLOSE, HIGH, LOW, CLOSE_OPEN, HIGH_LOW
}


