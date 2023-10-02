package com.mawla.marvelcharacterkotlinapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mawla.marvelcharacterkotlinapp.Enums.CharactersInfoViewState
import com.mawla.marvelcharacterkotlinapp.AppUtils.Constants
import com.mawla.marvelcharacterkotlinapp.Dtos.*
import com.mawla.marvelcharacterkotlinapp.Enums.CharactersInfoViewType
import com.mawla.marvelcharacterkotlinapp.WebservicesPackage.WebservicesClass
import com.mawla.marvelcharacterkotlinapp.databinding.FragmentItemDetailBinding

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [MarvelCharactersListFragment]
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
class MarvelCharacterDetailsFragment : Fragment(), View.OnClickListener {

    private var selectedCharacterId: Int = 0
    private var selectedCharacterName: String? = null

    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!

    private val webservicesClass = WebservicesClass()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(Constants.CHARACTER_ID_BUNDLE_KEY)) {
                selectedCharacterId = it.getInt(Constants.CHARACTER_ID_BUNDLE_KEY)
                selectedCharacterName = it.getString(Constants.CHARACTER_NAME_BUNDLE_KEY)
            }
        }
        webservicesClass.initWebservicesClass()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateInitialContent()
        callGetCharacterDetails()
    }

    private fun updateInitialContent() {
        _binding?.characterNameTextView?.text = selectedCharacterName
        _binding?.retryButton?.setOnClickListener(this)
        _binding?.backArrowImageView?.setOnClickListener(this)
        _binding?.marvelCharacterComicsInfoView?.initViewHeaderByType(CharactersInfoViewType.Comics,this)
        _binding?.marvelCharacterEventsInfoView?.initViewHeaderByType(CharactersInfoViewType.Events,this)
        _binding?.marvelCharacterSeriesInfoView?.initViewHeaderByType(CharactersInfoViewType.Series,this)
        _binding?.marvelCharacterStoriesInfoView?.initViewHeaderByType(CharactersInfoViewType.Stories,this)
    }

    private fun callGetCharacterDetails() {
        _binding?.marvelCharacterMainDataContainer?.visibility = View.GONE
        _binding?.failureView?.visibility = View.GONE
        _binding?.detailsCircularLoader?.visibility = View.VISIBLE
        webservicesClass.callCharacterDetailsService(this, selectedCharacterId)
    }

    fun onGetCharacterDataSucceed(marvelCharacterDataWrapper: MarvelCharacterDataWrapper?) {
        if (marvelCharacterDataWrapper?.code == 200) {
            populateMarvelCharacterData(marvelCharacterDataWrapper.data?.results!!)
            _binding?.marvelCharacterMainDataContainer?.visibility = View.VISIBLE
            _binding?.failureView?.visibility = View.GONE
            _binding?.detailsCircularLoader?.visibility = View.GONE
            callCharacterInfoApis()
        } else {
            onGetCharacterDataFailed()
        }
        _binding?.detailsCircularLoader?.visibility = View.GONE
    }

    fun onGetCharacterDataFailed() {
        _binding?.detailsCircularLoader?.visibility = View.GONE
        _binding?.failureView?.visibility = View.VISIBLE
    }

    private fun populateMarvelCharacterData(marvelCharacterDtoList: List<MarvelCharacterDto>) {
        _binding?.characterProfileImageView?.setImageURI(marvelCharacterDtoList[0].thumbnail?.path + "." + marvelCharacterDtoList[0].thumbnail?.extension)
        _binding?.characterDescriptionTextView?.text = marvelCharacterDtoList[0].description
    }

    private fun callCharacterInfoApis() {
        callCharacterComicsService()
        callCharacterEventsService()
        callCharacterSeriesService()
        callCharacterStoriesService()
    }

    fun callCharacterComicsService() {
        _binding?.marvelCharacterComicsInfoView?.setViewState(CharactersInfoViewState.loading)
        webservicesClass.callCharacterComicsService(this, selectedCharacterId)
    }

    fun onGetCharacterComicsSucceed(marvelComicDataWrapper: MarvelComicDataWrapper?) {
        _binding?.marvelCharacterComicsInfoView?.populateViewByType(marvelComicDataWrapper)
    }

    fun onGetCharacterComicsFailed() {
        _binding?.marvelCharacterComicsInfoView?.setViewState(CharactersInfoViewState.failed)
    }

    fun callCharacterEventsService() {
        _binding?.marvelCharacterEventsInfoView?.setViewState(CharactersInfoViewState.loading)
        webservicesClass.callCharacterEventsService(this, selectedCharacterId)
    }

    fun onGetCharacterEventsSucceed(marvelEventDataWrapper: MarvelEventDataWrapper?) {
        _binding?.marvelCharacterEventsInfoView?.populateViewByType(marvelEventDataWrapper)
    }

    fun onGetCharacterEventsFailed() {
        _binding?.marvelCharacterEventsInfoView?.setViewState(CharactersInfoViewState.failed)
    }

    fun callCharacterSeriesService() {
        _binding?.marvelCharacterSeriesInfoView?.setViewState(CharactersInfoViewState.loading)
        webservicesClass.callCharacterSeriesService(this, selectedCharacterId)
    }

    fun onGetCharacterSeriesSucceed(marvelSeriesDataWrapper: MarvelSeriesDataWrapper?) {
        _binding?.marvelCharacterSeriesInfoView?.populateViewByType(marvelSeriesDataWrapper)
    }

    fun onGetCharacterSeriesFailed() {
        _binding?.marvelCharacterSeriesInfoView?.setViewState(CharactersInfoViewState.failed)
    }

    fun callCharacterStoriesService() {
        _binding?.marvelCharacterStoriesInfoView?.setViewState(CharactersInfoViewState.loading)
        webservicesClass.callCharacterStoriesService(this, selectedCharacterId)
    }

    fun onGetCharacterStoriesSucceed(marvelStoryDataWrapper: MarvelStoryDataWrapper?) {
        _binding?.marvelCharacterStoriesInfoView?.populateViewByType(marvelStoryDataWrapper)
    }

    fun onGetCharacterStoriesFailed() {
        _binding?.marvelCharacterStoriesInfoView?.setViewState(CharactersInfoViewState.failed)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.retry_button -> {
                callGetCharacterDetails()
            }
            R.id.back_arrow_image_view -> {
                findNavController()?.navigate(R.id.item_list_fragment, null)
            }
        }
    }
}