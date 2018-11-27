package mine.blog.kotlin.lab.rest

import mine.blog.kotlin.lab.handler.CourseHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.router

@Service
class CourseRouter (private val courseHandler: CourseHandler) {

    @Bean
    fun route() = router {
        ("/router/courses").nest {
            GET("/", courseHandler::getAllCourses)
            GET("/id/{id}", courseHandler::getCourse)
            GET("/name/{name}", courseHandler::getCourses)
            POST("/", courseHandler::addCourse)
            PUT("/", courseHandler::updateCourse)
            DELETE("/", courseHandler::deleteCourse)
        }
    }
}