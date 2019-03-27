package wordofday.crawler

import org.jsoup.Jsoup
import org.jsoup.select.Elements
import wordofday.domain.Word
import wordofday.domain.WordType
import java.time.LocalDate

const val WORD_OF_DAY_URL = "https://daily.wordreference.com"

class WordOfDayCrawler {

    fun get() : Map<WordType, Word> {
        var words: MutableMap<WordType, Word> = mutableMapOf()

        val wordOfDayPage = Jsoup.connect(WORD_OF_DAY_URL).get()
        println(wordOfDayPage.title())
        val elements: Elements? = wordOfDayPage.select(".post-header h2 a")
        elements?.forEach {
            val wordDetailHref = it.attr("href")

            if (isWordOfToday(wordDetailHref)) {

                var listOfMeanings: MutableList<String> = mutableListOf()
                var listOfExamples: MutableList<String> = mutableListOf()

                println("\n ${it.text()}")
                val wordOfDayDetailPage = Jsoup.connect(wordDetailHref).get()

                wordOfDayDetailPage.getElementsByClass("section text-area")
                    .select("p").forEach{meaning ->
                        listOfMeanings.add(meaning.text())
                    }

                wordOfDayDetailPage.getElementsByClass("section list-w-title").select("li").forEach { example ->
                    listOfExamples.add(example.text())
                    println("\n ${example.text()}")
                }

                if(it.text().contains("Basic")){
                    words.put(WordType.BASIC, Word(it.text(), listOfMeanings, listOfExamples))
                }else{
                    words.put(WordType.INTERMEDIATE, Word(it.text(), listOfMeanings, listOfExamples))
                }
            }
        }
        return words
    }

    private fun isWordOfToday(wordDetailHref: String): Boolean {
        return LocalDate.now().let {
            val month = if (it.monthValue < 10) "0${it.monthValue}" else "${it.monthValue}"
            val dayOfMonth = if (it.dayOfMonth < 10) "0${it.dayOfMonth}" else "${it.dayOfMonth}"
            wordDetailHref.startsWith("$WORD_OF_DAY_URL/${it.year}/$month/$dayOfMonth/")
        }
    }
}