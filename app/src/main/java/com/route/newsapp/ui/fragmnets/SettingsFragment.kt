package com.route.newsapp.ui.fragmnets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter
import com.route.newsapp.R
import com.route.newsapp.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {
    lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectLanguage()
    }

    private fun selectLanguage() {
        val languages = resources.getStringArray(R.array.languages)
        val adapter = ArrayAdapter(requireActivity(),R.layout.text_language,languages)
        binding.selectLanguage.setAdapter(adapter)
    }
}