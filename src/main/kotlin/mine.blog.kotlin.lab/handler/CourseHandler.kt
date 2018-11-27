package mine.blog.kotlin.lab.handler

import mine.blog.kotlin.lab.data.Course
import mine.blog.kotlin.lab.service.CourseService
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Service
class CourseHandler (private val courseService: CourseService) {
    fun getCourse(req: ServerRequest): Mono<ServerResponse> {
        val course = courseService.getCourse(req.pathVariable("id").toLong())
        return ServerResponse.ok().body(fromObject(course ?: Course()))
    }

    fun getCourses(req: ServerRequest): Mono<ServerResponse> = ServerResponse.ok().body(fromObject(courseService.getCourses(req
            .pathVariable("name"))))

    fun getAllCourses(req: ServerRequest): Mono<ServerResponse> = ServerResponse.ok().body(fromObject(courseService.getAllCourses()))

    fun addCourse(req: ServerRequest): Mono<ServerResponse> {
        val course = req.bodyToMono(Course::class.java).block()
        return ServerResponse.ok().body(fromObject(courseService.addCourse(course!!)))
    }

    fun updateCourse(req: ServerRequest): Mono<ServerResponse> {
        val course = req.bodyToMono(Course::class.java).block()
        return ServerResponse.ok().body(fromObject(courseService.updateCourse(course!!)))
    }

    fun deleteCourse(req: ServerRequest): Mono<ServerResponse> {
        val id = req.pathVariable("id").toLong()
        val course = courseService.deleteCourse(id)
        return ServerResponse.ok().body(fromObject(course ?: Course()))
    }
}