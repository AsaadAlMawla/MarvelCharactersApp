package com.mawla.marvelcharacterkotlinapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.mawla.marvelcharacterkotlinapp.Adapters.MarvelCharactersListViewAdapter
import com.mawla.marvelcharacterkotlinapp.AppUtils.Constants
import com.mawla.marvelcharacterkotlinapp.Dtos.MarvelCharacterDataWrapper
import com.mawla.marvelcharacterkotlinapp.Dtos.MarvelCharacterDto
import com.mawla.marvelcharacterkotlinapp.WebservicesPackage.WebservicesClass
import com.mawla.marvelcharacterkotlinapp.databinding.FragmentItemListBinding


class MarvelCharactersListFragment : Fragment(), AdapterView.OnItemClickListener,
    View.OnClickListener {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    private val webservicesClass = WebservicesClass()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webservicesClass.initWebservicesClass()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var itemDetailFragmentContainer: View? = null
    private var marvelCharactersListView: GridView? = null
    private var failureContainerView: LinearLayout? = null
    private var failureErrorTextView: TextView? = null
    private var failureRetryButton: Button? = null
    private var loaderView: LinearLayout? = null
    private var marvelCharactersTitleView: LinearLayout? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        marvelCharactersListView = view.findViewById(R.id.marvel_characters_list_view)
        itemDetailFragmentContainer = view.findViewById(R.id.item_detail_nav_container)
        loaderView = view.findViewById(R.id.circular_loader)
        failureContainerView = view.findViewById(R.id.failure_view)
        failureErrorTextView = view.findViewById(R.id.failure_error_text_view)
        failureRetryButton = view.findViewById(R.id.retry_button)
        marvelCharactersTitleView = view.findViewById(R.id.marvel_characters_title_container_view)

        marvelCharactersListView?.onItemClickListener = this
        failureRetryButton?.setOnClickListener(this)
        callGetCharactersList()
    }

    private fun callGetCharactersList() {
        loaderView?.visibility = View.VISIBLE
        failureContainerView?.visibility = View.GONE
        marvelCharactersTitleView?.visibility = View.GONE
        webservicesClass.callGetCharactersList(this)
    }

    fun onGetCharactersListSucceed(marvelCharacterDataWrapper: MarvelCharacterDataWrapper?) {
        if (marvelCharacterDataWrapper?.code == 200) {
            populateMarvelCharactersList(marvelCharacterDataWrapper.data?.results!!)
            marvelCharactersListView?.visibility = View.VISIBLE
            marvelCharactersTitleView?.visibility = View.VISIBLE
            failureContainerView?.visibility = View.GONE
        } else {
            onGetCharactersListFailed()
        }
        loaderView?.visibility = View.GONE
    }

    fun onGetCharactersListFailed() {
        loaderView?.visibility = View.GONE
        failureContainerView?.visibility = View.VISIBLE
    }

    private fun populateMarvelCharactersList(marvelCharacterDtoList: List<MarvelCharacterDto>) {
        val marvelCharactersListViewAdapter = MarvelCharactersListViewAdapter(
            this.context!!,
            R.layout.marvel_characters_list_view_item_layout
        )
        marvelCharactersListView?.adapter = marvelCharactersListViewAdapter
        marvelCharactersListViewAdapter.addAll(marvelCharacterDtoList)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, p3: Long) {
        when (adapterView?.id) {
            R.id.marvel_characters_list_view -> {
                val bundle = Bundle()
                bundle.putInt(
                    Constants.CHARACTER_ID_BUNDLE_KEY,
                    (adapterView.adapter.getItem(position) as MarvelCharacterDto).id
                )
                bundle.putString(
                    Constants.CHARACTER_NAME_BUNDLE_KEY,
                    (adapterView.adapter.getItem(position) as MarvelCharacterDto).name
                )
                itemDetailFragmentContainer?.findNavController()
                    ?.navigate(R.id.fragment_item_detail, bundle)
                    ?: view?.findNavController()?.navigate(R.id.show_item_detail, bundle)
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.retry_button -> {
                callGetCharactersList()
            }
        }
    }
}