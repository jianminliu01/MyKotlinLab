package mine.blog.kotlin.lab.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Course(@Id val id: Long, val name: String, val subject: String, val successful: Boolean, val createdBy: String, val createdDate: Date) {
        constructor() : this(-1, "", "", false, "", Date())
}