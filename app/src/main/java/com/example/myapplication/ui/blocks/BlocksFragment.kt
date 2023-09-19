package com.example.myapplication.ui.blocks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentBlocksBinding

class BlocksFragment : Fragment() {

    private var _binding: FragmentBlocksBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val blocksViewModel =
            ViewModelProvider(this).get(BlocksViewModel::class.java)

        _binding = FragmentBlocksBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textBlocks
        blocksViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}