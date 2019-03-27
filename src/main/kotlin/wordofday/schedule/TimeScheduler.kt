package wordofday.schedule

//import wordofday.crawler.WordOfDayCrawler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

const val EVERY_NINE_AM_ON_WEEK = "0 0 8-10 * * MON-FRI"
const val ON_EACH_TEN_SECONDS = "*/10 * * * * * "

const val TIME_ZONE_SP = "America/Sao_Paulo"

//@Component
//@EnableScheduling
//class TimeScheduler {
//
//    @Autowired
//    lateinit var wordOfDayController: WordOfDayCrawler
//
//    @Scheduled(cron = ON_EACH_TEN_SECONDS, zone = TIME_ZONE_SP)
//    fun getWordOfDay() = wordOfDayController.get()//println(LocalDateTime.now())
//}