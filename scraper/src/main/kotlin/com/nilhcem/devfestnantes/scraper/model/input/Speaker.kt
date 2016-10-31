package com.nilhcem.devfestnantes.scraper.model.input

data class Speaker(val id: Long, val firstname: String, val name: String, val company: String, val bio: String, val photo: String, val social: SpeakerSocial, val sessions: List<Long>)
data class SpeakerSocial(val twitter: String, val googleplus: String, val github: String, val linkedin: String, val blog: String)
