package com.mawla.marvelcharacterkotlinapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.mawla.marvelcharacterkotlinapp.Dtos.MarvelCharacterDto
import com.mawla.marvelcharacterkotlinapp.R

class MarvelCharactersListViewAdapter(context: Context, resource: Int) :
    ArrayAdapter<MarvelCharacterDto>(context, resource) {

    val resource: Int = resource
    private val context: Context = context

    inner class MarvelCharacterDataHolder {
        lateinit var marvelCharacterImageView: SimpleDraweeView
        lateinit var marvelCharacterNameTextView: TextView
        lateinit var marvelCharacterIdTextView: TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view: View? = convertView
        var holder: MarvelCharacterDataHolder? = null

        if (view == null) {
            view = LayoutInflater.from(context).inflate(resource, parent, false)
            holder = MarvelCharacterDataHolder()

            holder.marvelCharacterImageView = view.findViewById(R.id.character_profile_image_view)
            holder.marvelCharacterNameTextView = view.findViewById(R.id.character_name_text_view)
            holder.marvelCharacterIdTextView = view.findViewById(R.id.character_id_text_view)

            view.tag = holder
        } else {
            holder = view.tag as MarvelCharacterDataHolder?
        }

        val item = getItem(position)
        holder?.marvelCharacterImageView?.setImageURI(item?.thumbnail?.path + "." + item?.thumbnail?.extension)
        holder?.marvelCharacterNameTextView?.text = item?.name
        holder?.marvelCharacterIdTextView?.text = item?.description

        return view!!
    }
}