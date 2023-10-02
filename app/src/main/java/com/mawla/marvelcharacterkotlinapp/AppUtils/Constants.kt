package com.mawla.marvelcharacterkotlinapp.AppUtils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object Constants {

    const val MARVEL_GATEWAY_BASE_URL = "https://gateway.marvel.com"
    const val PUBLIC_KEY = "33fbc97a2fc3ccbfb8bcd17627a607d6"
    private const val PRIVATE_KEY = "1a7161e9e6953f80acc8d5287565621d7819c2b5"

    val tsLong: Long = System.currentTimeMillis()
    val hashedKey: String? = md5(tsLong.toString() + PRIVATE_KEY + PUBLIC_KEY)

    val CHARACTER_ID_BUNDLE_KEY: String? = "CHARACTER_ID_BUNDLE_KEY"
    val CHARACTER_NAME_BUNDLE_KEY: String? = "CHARACTER_NAME_BUNDLE_KEY"


    private fun md5(s: String): String? {
        val MD5 = "MD5"
        try {
            val digest = MessageDigest.getInstance(MD5)
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

}