package wordofday.crawler

import org.jsoup.Jsoup
import org.jsoup.select.Elements

class WordOfDayCrawler {
    fun crawler(){
        val document = Jsoup.connect("https://daily.wordreference.com/").get()
        println(document.title())
        val elements: Elements? = document.select("container content .post")
        elements?.forEach {
            println(it.attr(""))
        }
    }
}