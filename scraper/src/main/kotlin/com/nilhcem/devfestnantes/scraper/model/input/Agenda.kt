package com.nilhcem.devfestnantes.scraper.model.input

data class Agenda(val day1: List<AgendaHour>, val day2: List<AgendaHour>)
data class AgendaHour(val id: Int, val label: String)
