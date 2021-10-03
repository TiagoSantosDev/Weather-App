package com.tiagosantos.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tiagosantos.weatherapp.databinding.FragmentSecondBinding
import kotlinx.android.synthetic.main.humidity_detail_item.*
import kotlinx.android.synthetic.main.pressure_detail_item.*
import kotlinx.android.synthetic.main.wind_detail_item.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: WeatherDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSecondBinding.inflate(
            layoutInflater,
            container,
            false
        ).let {
            binding = it
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.weatherRepo.observe(
            viewLifecycleOwner
        ) { weather ->
            with(binding) {
                cityName.text = weather.currentWeather.toString()
                humidity.text = weather.currentWeather.toString()
            }
        }
    }
}
