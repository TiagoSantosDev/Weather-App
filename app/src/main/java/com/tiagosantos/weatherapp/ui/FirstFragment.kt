package com.tiagosantos.weatherapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tiagosantos.weatherapp.adapters.TimeLineAdapter
import com.tiagosantos.weatherapp.databinding.FragmentFirstBinding
import com.tiagosantos.weatherapp.models.CurrentWeather
import com.tiagosantos.weatherapp.models.Orientation
import com.tiagosantos.weatherapp.models.TimelineAttributes
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: FirstFragmentViewModel by activityViewModels()

    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mAttributes: TimelineAttributes

    private var weatherDataList = MutableLiveData<CurrentWeather>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // fetches the current coordinates of the user location
        viewModel.getCurrentLocation(requireActivity().applicationContext, activity)
        return FragmentFirstBinding.inflate(layoutInflater, container, false)
            .let {
                binding = it
                binding.root
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ctx = context
        if (ctx != null) {
            initRecyclerView(ctx)
        }

        mAttributes.onOrientationChanged = { oldValue, newValue ->
            if (oldValue != newValue) initRecyclerView(ctx!!)
        }

        mAttributes.orientation = Orientation.VERTICAL
    }

    private fun initRecyclerView(ctx: Context) {
        initAdapter(ctx)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            @SuppressLint("LongLogTag")
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (recyclerView.getChildAt(0).top < 0) dropshadow.visibility = VISIBLE
            }
        })
    }

    private fun initAdapter(ctx: Context) {
        mLayoutManager = if (mAttributes.orientation == Orientation.HORIZONTAL) {
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        } else {
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        }

        recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = TimeLineAdapter(weatherDataList, mAttributes, ctx)
        }
    }
}
