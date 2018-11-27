package mine.blog.kotlin.lab.repository.reactive

import mine.blog.kotlin.lab.data.Course
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux


interface CourseMongoRepository  : ReactiveMongoRepository<Course, Long> {
    fun findByName(name: String): Flux<Course>
}