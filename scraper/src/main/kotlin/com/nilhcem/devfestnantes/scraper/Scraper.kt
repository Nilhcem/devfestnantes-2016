package com.nilhcem.devfestnantes.scraper

import com.nilhcem.devfestnantes.scraper.api.DevFestApi
import com.nilhcem.devfestnantes.scraper.model.Mapper
import com.nilhcem.devfestnantes.scraper.model.output.Session
import com.nilhcem.devfestnantes.scraper.model.output.Speaker

class Scraper(api: DevFestApi) {

    var speakers: List<Speaker>
    var sessions: List<Session>

    init {
        println("Start scraping")

        val schedule = api.getSchedule().execute().body()
        val speakersIdMap = mutableMapOf<Long, Int>()

        speakers = schedule.speakers
                .mapIndexed { i, speaker ->
                    speakersIdMap.put(speaker.id, i + 1)
                    Mapper.convertSpeaker(i, speaker)
                }

        sessions = schedule.sessions
                .mapIndexed { i, session ->
                    Mapper.convertSession(i, session, schedule.agenda, speakersIdMap)
                }
    }
}
