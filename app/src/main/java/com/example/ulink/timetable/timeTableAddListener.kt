package com.example.ulink.timetable

import com.example.ulink.repository.TimeTable

interface timeTableAddListener {
    fun onAdded(timeTable :TimeTable)
}