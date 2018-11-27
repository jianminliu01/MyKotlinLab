package mine.blog.kotlin.lab.repository

import mine.blog.kotlin.lab.data.Course
import mine.blog.kotlin.lab.util.initCourse
import mine.blog.kotlin.lab.util.toDate
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.*

@Service
@Qualifier("CourseMapRepository")
class CourseMapRepository : CourseRepository {
    val courseMap = initCourse()

    override fun getCourse(id: Long): Course? {
        return courseMap.get(id)
    }

    override fun getCourses(name: String): List<Course> {
        return courseMap.values.filter { it.name == name }
    }

    override fun getAllCourses(): List<Course> {
        return courseMap.values.toMutableList()
    }

    override fun addCourse(course: Course): Course {
        var maxId = courseMap.keys.max()?: + 1
        val newCourse = course.copy(id = maxId)
        courseMap.put(maxId, newCourse)
        return newCourse
    }

    override fun updateCourse(course: Course): Course {
        courseMap.put(course.id, course)
        return course
    }

    override fun deleteCourse(id: Long): Course? {
        val course = courseMap.remove(id)
        return course
    }
}