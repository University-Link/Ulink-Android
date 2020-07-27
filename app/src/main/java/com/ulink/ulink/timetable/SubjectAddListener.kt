package com.ulink.ulink.timetable

import com.ulink.ulink.repository.Subject

interface SubjectAddListener {
    fun onAdded(subject : Subject)
}