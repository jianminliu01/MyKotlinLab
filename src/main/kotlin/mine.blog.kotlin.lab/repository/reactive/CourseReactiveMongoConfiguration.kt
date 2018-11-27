package mine.blog.kotlin.lab.repository.reactive

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = arrayOf(CourseMongoRepository::class))
class CourseReactiveMongoConfiguration
