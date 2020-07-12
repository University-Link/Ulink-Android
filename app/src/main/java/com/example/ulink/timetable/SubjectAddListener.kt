package com.example.ulink.timetable

import com.example.ulink.repository.Subject

interface SubjectAddListener {
    fun onAdded(subject : Subject)
}