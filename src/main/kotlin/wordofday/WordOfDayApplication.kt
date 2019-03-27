package wordofday

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.meta.TelegramBotsApi
import wordofday.crawler.WordOfDayCrawler
import wordofday.domain.Word
import wordofday.domain.WordType
import wordofday.service.TelegramBot

@SpringBootApplication
class WordOfDayApplication {

    companion object {
        lateinit var words: Map<WordType, Word>
    }
}

fun main(args:Array<String>) {
    ApiContextInitializer.init()
    SpringApplication.run(WordOfDayApplication::class.java, *args)

    WordOfDayApplication.words = WordOfDayCrawler().get()
}

