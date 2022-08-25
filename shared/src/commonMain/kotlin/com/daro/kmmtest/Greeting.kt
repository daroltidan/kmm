package com.daro.kmmtest

import kotlinx.datetime.*

class Greeting {
    fun greeting(): String {
        return "Welcome to ${Platform().platform}!" +
                "\nThere are only ${daysUntilNewYear()} left until  🎅🏼 "
    }

    private fun daysUntilNewYear(): Int {
        val today = Clock.System.todayAt(TimeZone.currentSystemDefault())
        val closestChristmas = LocalDate(today.year, 12, 25)
        return today.daysUntil(closestChristmas)
    }

}
