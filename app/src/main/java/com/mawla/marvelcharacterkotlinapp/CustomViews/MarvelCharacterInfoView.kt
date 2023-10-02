package com.mawla.marvelcharacterkotlinapp.CustomViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.mawla.marvelcharacterkotlinapp.Adapters.MarvelCharacterInfoListViewAdapter
import com.mawla.marvelcharacterkotlinapp.Enums.CharactersInfoViewState
import com.mawla.marvelcharacterkotlinapp.Enums.CharactersInfoViewType
import com.mawla.marvelcharacterkotlinapp.Interfaces.InfoItemInterface
import com.mawla.marvelcharacterkotlinapp.Interfaces.MarvelCharactersInfoInterface
import com.mawla.marvelcharacterkotlinapp.MarvelCharacterDetailsFragment
import com.mawla.marvelcharacterkotlinapp.R
import com.mawla.marvelcharacterkotlinapp.databinding.MarvelCharacterInfoViewLayoutBinding


class MarvelCharacterInfoView(context: Context?, attrs: AttributeSet?) :
    RelativeLayout(context, attrs), View.OnClickListener {

    private var _binding: MarvelCharacterInfoViewLayoutBinding? = null
    private var charactersInfoViewType: CharactersInfoViewType? = null
    private var marvelCharacterDetailsFragment: MarvelCharacterDetailsFragment? = null

    init {
        val inflater: LayoutInflater =
            context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        _binding = MarvelCharacterInfoViewLayoutBinding.inflate(inflater, this, true)
        _binding?.retryButton?.setOnClickListener(this)
    }

    fun initViewHeaderByType(charactersInfoViewType: CharactersInfoViewType,marvelCharacterDetailsFragment: MarvelCharacterDetailsFragment?) {
        this.charactersInfoViewType = charactersInfoViewType
        this.marvelCharacterDetailsFragment = marvelCharacterDetailsFragment
        _binding?.characterInfoTitleTextView?.text = charactersInfoViewType.toString()
        when (charactersInfoViewType) {
            CharactersInfoViewType.Comics -> {
                _binding?.characterInfoImageView?.setImageResource(R.drawable.comic_face_icon)
            }
            CharactersInfoViewType.Events -> {
                _binding?.characterInfoImageView?.setImageResource(R.drawable.event_icon)
            }
            CharactersInfoViewType.Stories -> {
                _binding?.characterInfoImageView?.setImageResource(R.drawable.story_icon)
            }
            CharactersInfoViewType.Series -> {
                _binding?.characterInfoImageView?.setImageResource(R.drawable.series_icon)
            }
        }
    }

    fun setViewState(charactersInfoViewState: CharactersInfoViewState) {
        when (charactersInfoViewState) {
            CharactersInfoViewState.succeeded -> {
                _binding?.circularLoader?.visibility = GONE
                _binding?.characterInfoListView?.visibility = VISIBLE
                _binding?.failureView?.visibility = GONE
            }
            CharactersInfoViewState.loading -> {
                _binding?.circularLoader?.visibility = VISIBLE
                _binding?.characterInfoListView?.visibility = GONE
                _binding?.failureView?.visibility = GONE
            }
            CharactersInfoViewState.failed -> {
                _binding?.circularLoader?.visibility = GONE
                _binding?.characterInfoListView?.visibility = GONE
                _binding?.failureView?.visibility = VISIBLE
            }
        }

    }

    fun populateViewByType(marvelCharactersInfoInterface: MarvelCharactersInfoInterface?) {
        setViewState(CharactersInfoViewState.succeeded)
        populateListView(marvelCharactersInfoInterface?.returnListFromWrapper())
    }

    private fun populateListView(infoItemInterfaceList: List<InfoItemInterface>?) {
        val marvelCharacterInfoListViewAdapter = MarvelCharacterInfoListViewAdapter(context, R.layout.marvel_character_info_list_view_item_layout, charactersInfoViewType)
        _binding?.characterInfoListView?.adapter = marvelCharacterInfoListViewAdapter
        marvelCharacterInfoListViewAdapter.addAll(infoItemInterfaceList!!)
    }

    override fun onClick(p0: View?) {
        when(charactersInfoViewType){
            CharactersInfoViewType.Comics -> {
                marvelCharacterDetailsFragment?.callCharacterComicsService()
            }
            CharactersInfoViewType.Events -> {
                marvelCharacterDetailsFragment?.callCharacterEventsService()
            }
            CharactersInfoViewType.Stories -> {
                marvelCharacterDetailsFragment?.callCharacterStoriesService()
            }
            CharactersInfoViewType.Series -> {
                marvelCharacterDetailsFragment?.callCharacterSeriesService()
            }
            null -> {}
        }
    }

}