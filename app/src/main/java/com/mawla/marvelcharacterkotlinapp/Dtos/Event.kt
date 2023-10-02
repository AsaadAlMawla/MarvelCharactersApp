package com.mawla.marvelcharacterkotlinapp.Dtos

import com.mawla.marvelcharacterkotlinapp.Interfaces.InfoItemInterface
import java.util.*

class Event : InfoItemInterface {
    var id = 0
    var title: String? = null
    var description: String? = null
    var start: Date? = null
    var end: Date? = null

    override fun grabInfoName(): String? {
        return title
    }

    override fun grabInfoDescription(): String? {
        return description
    }

}