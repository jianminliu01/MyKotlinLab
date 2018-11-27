package mine.blog.kotlin.lab.service

import mine.blog.kotlin.lab.data.Course
import mine.blog.kotlin.lab.repository.CourseRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class CourseService (@Qualifier("CourseMapRepository") private val courseMapRepository: CourseRepository) {

    @Value("\${mine.blog.kotlin.lab.repository}")
    var courseRepository: String? = null

    fun getCourse(id: Long): Course? = getCourseRepository().getCourse(id)

    fun getCourses(name: String): List<Course> = getCourseRepository().getCourses(name)

    fun getAllCourses(): List<Course> = getCourseRepository().getAllCourses()

    fun addCourse(course: Course): Course = getCourseRepository().addCourse(course)

    fun updateCourse(course: Course): Course = getCourseRepository().updateCourse(course)

    fun deleteCourse(id: Long): Course? = getCourseRepository().deleteCourse(id)

    private fun getCourseRepository(): CourseRepository = when (courseRepository) {
        "map" -> courseMapRepository
        else -> courseMapRepository
    }
}