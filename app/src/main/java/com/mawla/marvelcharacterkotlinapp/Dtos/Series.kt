package com.mawla.marvelcharacterkotlinapp.Dtos

import com.mawla.marvelcharacterkotlinapp.Interfaces.InfoItemInterface

class Series : InfoItemInterface {
    var id = 0
    var title: String? = null
    var description: String? = null

    override fun grabInfoName(): String? {
        return title
    }

    override fun grabInfoDescription(): String? {
        return description
    }
}