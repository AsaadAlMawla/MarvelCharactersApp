package com.mawla.marvelcharacterkotlinapp.Dtos

import com.mawla.marvelcharacterkotlinapp.Interfaces.InfoItemInterface
import com.mawla.marvelcharacterkotlinapp.Interfaces.MarvelCharactersInfoInterface

class MarvelComicDataWrapper : MarvelCharactersInfoInterface {
    var code = 0
    var status: String? = null
    var attributionText: String? = null
    var data: ComicDataContainer? = null

    override fun returnListFromWrapper(): List<InfoItemInterface>? {
        return if(data?.results?.size!! > 3) {
            data?.results?.subList(0, 3)
        }else{
            data?.results
        }
    }
}