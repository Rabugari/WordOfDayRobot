package wordofday.controller

import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WordOfDayController {

    @GetMapping("/crawler")
    fun get(){
        val wordOfDayPage = Jsoup.connect("https://daily.wordreference.com/").get()
        println(wordOfDayPage.title())
        val elements: Elements? = wordOfDayPage.select(".post-header h2 a")
        elements?.forEach {
            val wordDetailHref = it.attr("href")

            println(it.text())
            println("Link: $wordDetailHref")
            val wordOfDayDetailPage = Jsoup.connect(wordDetailHref).get()
            val divExamples = wordOfDayDetailPage.getElementsByClass("section list-w-title")
            println("PAis? ${divExamples.parents().select("p")}")
            println("Examples ${divExamples.select(".single ul li p").text()}")
        }
    }
}