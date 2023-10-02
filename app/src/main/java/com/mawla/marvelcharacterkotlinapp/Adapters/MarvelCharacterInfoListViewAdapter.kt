package com.mawla.marvelcharacterkotlinapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.mawla.marvelcharacterkotlinapp.Enums.CharactersInfoViewType
import com.mawla.marvelcharacterkotlinapp.Interfaces.InfoItemInterface
import com.mawla.marvelcharacterkotlinapp.MarvelCharacterDetailsFragment
import com.mawla.marvelcharacterkotlinapp.R

class MarvelCharacterInfoListViewAdapter(
    context: Context,
    resource: Int,
    charactersInfoViewType: CharactersInfoViewType?
) :
    ArrayAdapter<InfoItemInterface>(context, resource) {

    private var resource: Int = 0
    private var context: Context? = null
    private var charactersInfoViewType: CharactersInfoViewType? = null

    init {
        this.resource = resource
        this.context = context
        this.charactersInfoViewType = charactersInfoViewType
    }

    inner class MarvelCharacterDataHolder {
        lateinit var marvelCharacterInfoNumberTextView: TextView
        lateinit var marvelCharacterInfoTitleTextView: TextView
        lateinit var marvelCharacterInfoDescriptionTextView: TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        var holder: MarvelCharacterDataHolder? = null
        if (view == null) {
            view = LayoutInflater.from(context).inflate(resource, parent, false)
            holder = MarvelCharacterDataHolder()
            holder.marvelCharacterInfoNumberTextView =
                view.findViewById(R.id.number_value_text_view)
            holder.marvelCharacterInfoTitleTextView = view.findViewById(R.id.title_value_text_view)
            holder.marvelCharacterInfoDescriptionTextView =
                view.findViewById(R.id.description_value_text_view)
            view.tag = holder
        } else {
            holder = view.tag as MarvelCharacterDataHolder?
        }

        val item = getItem(position)
        val numbering: Int = position + 1
        holder?.marvelCharacterInfoNumberTextView?.text = generateNumberingText(numbering)
        holder?.marvelCharacterInfoTitleTextView?.text = item?.grabInfoName()
        holder?.marvelCharacterInfoDescriptionTextView?.text = item?.grabInfoDescription()

        return view!!
    }

    private fun generateNumberingText(numbering: Int): String {
        return "${charactersInfoViewType.toString()} #$numbering"
    }
}