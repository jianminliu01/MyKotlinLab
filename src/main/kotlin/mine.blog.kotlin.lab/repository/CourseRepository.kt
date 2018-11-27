package mine.blog.kotlin.lab.repository

import mine.blog.kotlin.lab.data.Course

interface CourseRepository {
    fun getCourse(id: Long): Course?
    fun getCourses(name: String): List<Course>
    fun getAllCourses(): List<Course>
    fun addCourse(course: Course): Course
    fun updateCourse(course: Course): Course
    fun deleteCourse(id: Long): Course?
}