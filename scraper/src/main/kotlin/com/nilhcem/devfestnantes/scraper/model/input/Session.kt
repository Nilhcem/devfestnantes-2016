package com.nilhcem.devfestnantes.scraper.model.input

data class Session(val id: Long, val name: String, val description: String?, val speaker: List<Long>?, val agenda: SessionAgenda)
data class SessionAgenda(val day: Int, val hour: Int, val room: Int)
