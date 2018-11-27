package mine.blog.kotlin.lab.rest

    import mine.blog.kotlin.lab.data.Course
    import mine.blog.kotlin.lab.repository.reactive.CourseMongoRepository
    import mine.blog.kotlin.lab.util.initCourse
    import org.springframework.http.MediaType
    import org.springframework.web.bind.annotation.*
    import reactor.core.publisher.Flux
    import reactor.core.publisher.Mono

    @RestController
    @RequestMapping("/emitter")
    class CourseEmitter (private val courseMongoRepository: CourseMongoRepository) {
        //For developing and testing only.
        init{
            initCourse().forEach{courseMongoRepository.save(it.value).subscribe()}
        }

    @GetMapping(path = ["/courses/id/{id}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    @ResponseBody
    fun getCourseById(@PathVariable(name="id") id: Long): Mono<Course> {
        return courseMongoRepository.findById(id)
    }

    @GetMapping(path = ["/courses/name/{name}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    @ResponseBody
    fun getCoursesByName(@PathVariable(name="name") name: String): Flux<Course> {
        return courseMongoRepository.findByName(name)
    }

    @GetMapping("/courses", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    @ResponseBody
    fun getAllCourses(): Flux<Course> {
        return courseMongoRepository.findAll()
    }

    @PostMapping("/courses", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    @ResponseBody
    fun addCourse(@RequestBody course: Course): Mono<Course> {
        return courseMongoRepository.save(course)
    }

    @PutMapping("/courses", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    @ResponseBody
    fun putCourse(@RequestBody course: Course): Mono<Course> {
        return courseMongoRepository.save(course)
    }

    @DeleteMapping("/courses", produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    @ResponseBody
    fun putCourse(@RequestBody id: Long): Mono<Void> {
        return courseMongoRepository.deleteById(id)
    }
}