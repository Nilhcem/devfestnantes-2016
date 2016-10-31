package com.nilhcem.devfestnantes.scraper.model

import com.nilhcem.devfestnantes.scraper.model.input.Agenda
import com.nilhcem.devfestnantes.scraper.model.input.SessionAgenda
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.safety.Whitelist
import kotlin.text.RegexOption.IGNORE_CASE
import com.nilhcem.devfestnantes.scraper.model.input.Session as ApiSession
import com.nilhcem.devfestnantes.scraper.model.input.Speaker as ApiSpeaker
import com.nilhcem.devfestnantes.scraper.model.output.Session as AppSession
import com.nilhcem.devfestnantes.scraper.model.output.Speaker as AppSpeaker

object Mapper {

    fun convertSpeaker(id: Int, speaker: ApiSpeaker): AppSpeaker {
        val name = "${speaker.firstname} ${speaker.name}".trim()
        val title = speaker.company
        val photo = (if (speaker.firstname.isBlank()) null else "https://devfest.gdgnantes.com/images/speakers/${speaker.photo}") ?: ""
        val bio = speaker.bio.parseHtml()
        val website = if (speaker.social.blog.isNullOrBlank()) speaker.social.googleplus.nullIfBlank() else speaker.social.blog.nullIfBlank()
        val twitter = with(speaker.social.twitter.nullIfBlank()) { if (this == null) this else if (startsWith("@")) substring(1) else getHandleFromUrl(this) }
        val github = with(speaker.social.github.nullIfBlank()) { if (this == null) this else getHandleFromUrl(this) }

        return AppSpeaker(id + 1, name, title, photo, bio, website, twitter, github)
    }

    fun convertSession(id: Int, session: ApiSession, agenda: Agenda, speakersIdMap: Map<Long, Int>): AppSession {
        val title = session.name.parseHtml()
        val description = session.description?.parseHtml()
        val speakersId = session.speaker?.map { speakersIdMap[it]!! }
        val startAt = getStartAt(session.agenda, agenda)
        val duration = getDuration(session.agenda, agenda)
        val roomId = session.agenda.room

        return AppSession(id + 1, title, description, speakersId, startAt, duration, roomId)
    }

    private fun getStartAt(sessionAgenda: SessionAgenda, agenda: Agenda): String {
        val day: String
        val hour: String

        when (sessionAgenda.day) {
            1 -> {
                day = "2016-11-09"
                hour = agenda.day1.filter { it.id == sessionAgenda.hour }.single().label
            }
            2 -> {
                day = "2016-11-10"
                hour = agenda.day2.filter { it.id == sessionAgenda.hour }.single().label
            }
            else -> throw RuntimeException("Day must be 1 or 2")
        }

        return "$day ${(if (hour.length == 4) "0$hour" else hour).replace("h", ":")}"
    }

    private fun getDuration(sessionAgenda: SessionAgenda, agenda: Agenda) = when (sessionAgenda.day) {
        1 -> when (sessionAgenda.hour) {
            1 -> 60
            5 -> 120
            9, 10 -> 20
            11 -> 270
            else -> 50
        }
        2 -> when (sessionAgenda.hour) {
            1 -> 30
            5 -> 110
            8 -> 20
            10 -> 30
            else -> 50
        }
        else -> throw RuntimeException("Day must be 1 or 2")
    }

    private fun getHandleFromUrl(url: String?): String? {
        if (url == null || url.length == 0) {
            return null
        }

        val urlWithoutLastSlash = if (url.last() == '/') url.substring(0, url.length - 1) else url
        return urlWithoutLastSlash.substring(urlWithoutLastSlash.lastIndexOf("/") + 1)
    }

    private fun String.parseHtml() = Jsoup.clean(this, "", Whitelist.basic(),
            Document.OutputSettings().prettyPrint(false))
            .replace(Regex("&nbsp;", IGNORE_CASE), " ")
            .replace(Regex("&amp;", IGNORE_CASE), "&")
            .replace(Regex("&gt;", IGNORE_CASE), ">").replace(Regex("&lt;", IGNORE_CASE), "<")
            .replace(Regex("<br[\\s/]*>", IGNORE_CASE), "\n")
            .replace(Regex("<p>", IGNORE_CASE), "").replace(Regex("</p>", IGNORE_CASE), "\n")
            .replace(Regex("</?ul>", IGNORE_CASE), "")
            .replace(Regex("<li>", IGNORE_CASE), "• ").replace(Regex("</li>", IGNORE_CASE), "\n")
            .replace(Regex("\n\n• ", IGNORE_CASE), "\n• ")
            .replace(Regex("<a\\s[^>]*>", IGNORE_CASE), "").replace(Regex("</a>", IGNORE_CASE), "")
            .replace(Regex("</?b>", IGNORE_CASE), "")
            .replace(Regex("</?pre>", IGNORE_CASE), "")
            .replace(Regex("</?strong>", IGNORE_CASE), "")
            .replace(Regex("</?i>", IGNORE_CASE), "")
            .replace(Regex("</?em>", IGNORE_CASE), "")
            .replace(Regex("\\s*\n\\s*"), "\n").replace(Regex("^\n"), "").replace(Regex("\n$"), "")

    private fun String.nullIfBlank() = if (isNullOrBlank()) null else this
}
