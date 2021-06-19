package com.example.geckoweatherapp.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.geckoweatherapp.databinding.FragmentCityBinding

class CityFragment : Fragment() {

    private var _binding: FragmentCityBinding? = null
    private val binding get() = _binding!!

    private val args: CityFragmentArgs by navArgs()
    private val cityViewModel: CityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cityViewModel.weatherLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE

            if (it == null) {
                return@observe
            }

            binding.weatherTextView.text = it.toString()
        }

        val bookmark = args.bookmark

        binding.progressBar.visibility = View.VISIBLE
        cityViewModel.getWeatherForLocation(bookmark)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}