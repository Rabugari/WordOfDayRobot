package wordofday

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class WordOfDayApplication

fun main(args:Array<String>) {
    SpringApplication.run(WordOfDayApplication::class.java, *args)
}