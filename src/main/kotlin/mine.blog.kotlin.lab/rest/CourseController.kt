package mine.blog.kotlin.lab.rest

import mine.blog.kotlin.lab.data.Course
import mine.blog.kotlin.lab.service.CourseService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/controller")
class CourseController (private val courseService: CourseService) {
    @GetMapping(path = ["/courses/id/{id}"],
            produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    @ResponseBody
    fun getCourseById(@PathVariable(name="id") id: Long): Flux<Course> {
        return Flux.just(courseService.getCourse(id) ?: Course())
    }

    @GetMapping(path = ["/courses/name/{name}"],
            produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    @ResponseBody
    fun getCoursesByName(@PathVariable(name="name") name: String): Flux<Course> {
        return Flux.fromIterable(courseService.getCourses(name))
    }

    @GetMapping("/courses",
            produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    @ResponseBody
    fun getAllCourses(): Flux<Course> {
        return Flux.fromIterable(courseService.getAllCourses())
    }

    @PostMapping("/courses",
            produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    @ResponseBody
    fun addCourse(@RequestBody course: Course): Mono<Course> {
        return Mono.just(courseService.addCourse(course))
    }

    @PutMapping("/courses",
            produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    @ResponseBody
    fun putCourse(@RequestBody course: Course): Mono<Course> {
        return Mono.just(courseService.updateCourse(course))
    }

    @DeleteMapping("/courses",
            produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    @ResponseBody
    fun putCourse(@RequestBody id: Long): Mono<Course> {
        return Mono.just(courseService.deleteCourse(id)?: Course())
    }
}