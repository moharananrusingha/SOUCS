package com.oit.soucs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.oit.soucs.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private val args: ResultFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater,container,false)
        when(args.grade){
            0 -> binding.resultTitle.text = getText(R.string.inclusion_farmer)
            1 -> binding.resultTitle.text = getText(R.string.inclusion_rookie)
            2 -> binding.resultTitle.text = getText(R.string.inclusion_champion)
            3 -> binding.resultTitle.text = getText(R.string.inclusion_captain)
            4 -> binding.resultTitle.text = getText(R.string.inclusion_pro)
        }
        return binding.root
    }
}