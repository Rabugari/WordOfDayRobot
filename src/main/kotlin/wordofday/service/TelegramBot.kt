package wordofday.service

import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import wordofday.WordOfDayApplication
import wordofday.crawler.WordOfDayCrawler
import wordofday.domain.WordType

@Component
class TelegramBot : TelegramLongPollingBot() {

    override fun getBotToken(): String {
        return "884116006:AAE-eKGwnYF78-ECJhqztBF--peSlnqWXrk"
    }

    override fun onUpdateReceived(update: Update?) {
        update?.let {
            val message = it.message
            val chatId = message.chatId

            val sbMessage = StringBuffer()

            if ("basic".equals(message.getText(), true)) {
                sbMessage.append(WordOfDayApplication.words[WordType.BASIC]?.word)
                sbMessage.append("\n \n Meanings:")
                WordOfDayApplication.words[WordType.BASIC]?.meanings?.forEach {
                    sbMessage.append("\n \t $it")
                }
                sbMessage.append("\n \n Examples:")
                WordOfDayApplication.words[WordType.BASIC]?.examples?.forEach {
                    sbMessage.append("\n- $it")
                }
                execute(SendMessage(chatId, sbMessage.toString()))
            } else {
                sbMessage.append(WordOfDayApplication.words[WordType.INTERMEDIATE]?.word)
                sbMessage.append("\n \n Meanings:")
                WordOfDayApplication.words[WordType.INTERMEDIATE]?.meanings?.forEach {
                    sbMessage.append("\n \t $it")
                }
                sbMessage.append("\n \n Examples:")
                WordOfDayApplication.words[WordType.INTERMEDIATE]?.examples?.forEach {
                    sbMessage.append("\n- $it")
                }
                execute(SendMessage(chatId, sbMessage.toString()))
            }
        }
    }

    override fun getBotUsername(): String {
        return "WordOfDayRobot"
    }
}