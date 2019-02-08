package com.doublea.artzee.browse.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.doublea.artzee.R
import com.doublea.artzee.artdetail.view.ArtDetailFragment
import com.doublea.artzee.browse.viewmodel.MainActivityViewModel
import com.doublea.artzee.commons.data.models.Art
import com.doublea.artzee.commons.extensions.inflate
import com.doublea.artzee.commons.extensions.launchFragment
import kotlinx.android.synthetic.main.fragment_browse_art.*

class BrowseArtFragment : androidx.fragment.app.Fragment() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var adapter: ArtworkAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_browse_art)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        artwork_list.apply {
            setHasFixedSize(true)
            val gridLayout = androidx.recyclerview.widget.GridLayoutManager(context, 2)
            layoutManager = gridLayout
            clearOnScrollListeners()
        }

        initAdapter()
        viewModel.artList.observe(this, Observer<PagedList<Art>> { adapter.submitList(it) })
    }

    private fun initAdapter() {
        if (artwork_list.adapter == null) {
            adapter = ArtworkAdapter(activity, { art ->
                fragmentManager?.let { fm ->
                    val fragment = ArtDetailFragment()
                    val bundle = Bundle()
                    bundle.putParcelable("art", art)
                    fragment.arguments = bundle
                    fragment.launchFragment(fm)
                }
            }, 2)
        }
        artwork_list.adapter = adapter
    }
}