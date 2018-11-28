package mine.blog.kotlin.lab.service

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import mine.blog.kotlin.lab.util.initUrls
import mine.blog.kotlin.lab.util.log
import org.springframework.stereotype.Service
import java.net.MalformedURLException
import java.net.URL
import java.net.UnknownHostException
import java.util.concurrent.atomic.AtomicLong
import kotlin.system.measureTimeMillis

@Service
class CoroutineService {
    val sequenceTime : AtomicLong = AtomicLong()

    fun validateUrlsAsync(): Map<String, Boolean> = runBlocking {
        val results : MutableMap<String, Boolean> = mutableMapOf()
        val urls = initUrls()
        val deferreds = mutableListOf<Deferred<Pair<String, Boolean>>>()
        val ms = measureTimeMillis {
            val job = urls.forEach {
                deferreds += async(Dispatchers.Default) {
                    validateUrl(it)
                }
            }

            deferreds.forEach {
                val result = it.await()
                results.put(result.first, result.second)
            }
        }
        log("It took ${ms} ms to validate urls")
        log("It would take ${sequenceTime.get()} ms to validate urls sequentially")

        results
    }

    fun validateUrl(url: String): Pair<String, Boolean> {
        var result : Pair<String, Boolean> = url to true
        val ms = measureTimeMillis {
            try {
                val res = URL(url).openStream().use {
                    it.bufferedReader().readText()
                }
            } catch (e: UnknownHostException) {
                result = url to false
            } catch (e: MalformedURLException) {
                result = url to false
            }
        }
        log("It took ${ms} ms to validate url: ${url}")
        sequenceTime.getAndAdd(ms)
        return result
    }
}