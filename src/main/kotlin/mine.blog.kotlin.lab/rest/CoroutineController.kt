package mine.blog.kotlin.lab.rest

import mine.blog.kotlin.lab.service.CoroutineService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/controller/coroutine")
class CoroutineController(private val coroutineService: CoroutineService) {
    @GetMapping(path = ["/urls/validation"],
            produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun validateUrlsAsync(): Map<String, Boolean> {
      return coroutineService.validateUrlsAsync()
    }
}