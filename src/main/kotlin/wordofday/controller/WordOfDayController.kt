package wordofday.controller

import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

const val WORD_OF_DAY_URL = "https://daily.wordreference.com"

@RestController
class WordOfDayController {

    @GetMapping("/crawler")
    fun get() {
        val wordOfDayPage = Jsoup.connect(WORD_OF_DAY_URL).get()
        println(wordOfDayPage.title())
        val elements: Elements? = wordOfDayPage.select(".post-header h2 a")
        elements?.forEach {
            val wordDetailHref = it.attr("href")

            if (isWordOfToday(wordDetailHref)) {
                println("\n ${it.text()}")
                val wordOfDayDetailPage = Jsoup.connect(wordDetailHref).get()

                wordOfDayDetailPage.getElementsByClass("section list-w-title").select("li").forEach { example ->
                    println("\n ${example.text()}")
                }
            }
        }
    }

    private fun isWordOfToday(wordDetailHref: String): Boolean {
        return LocalDate.now().let {
            val month = if (it.monthValue < 10) "0${it.monthValue}" else "${it.monthValue}"
            val dayOfMonth = if (it.dayOfMonth < 10) "0${it.dayOfMonth}" else "${it.dayOfMonth}"
            wordDetailHref.startsWith("$WORD_OF_DAY_URL/${it.year}/$month/$dayOfMonth/")
        }
    }
}