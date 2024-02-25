package com.example.rowdyhacks.safetycard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.rowdyhacks.R
import com.example.rowdyhacks.databinding.FragmentSafetyCardBinding

class SafetyCardFragment : Fragment() {

    private lateinit var binding: FragmentSafetyCardBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_safety_card,
            container,
            false
        )
        // Inflate the layout for this fragment
        return binding.root
    }

}