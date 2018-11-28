package mine.blog.kotlin.lab.util

import mine.blog.kotlin.lab.data.Course
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.LinkedHashMap

fun String.toDate(pattern: String = "yyyy-MM-dd"): Date = SimpleDateFormat(pattern).parse(this)

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

//For developing and testing only.
fun initCourse() : LinkedHashMap<Long, Course> {
    val course1 = Course(1L, "Kotlin for Java Developers", "kotlin", true, "Sarah Ettritch", "2018-10-01".toDate())
    val course2 = Course(2L, "Kotlin: Using Coroutines", "kotlin", true, "Kevin Jones", "2018-08-01".toDate())
    val course3 = Course(3L, "The Complete Kotlin Developer Course", "kotlin", true, "Rob Percival", "2018-11-08".toDate())
    val course4 = Course(4L, "Spring WebFlux: Getting Started", "Springboot", true, "Esteban Herrera", "2018-08-30".toDate())
    return linkedMapOf(1L to course1, 2L to course2, 3L to course3, 4L to course4)
}

fun initUrls() : List<String> = mutableListOf("https://www.costco.com/", "https://notexist", "https://www.wayfair.com/", "https://www.furniture.com/", "httpna://invalid", "https://www.overstock.com/", "https://www.moneycrashers.com/", "https://www.walmart.com/", "https://www.bakerfurniture.com/", "https://www.roomstogo.com/", "https://www.target.com/", "https://www.homedepot.com/")