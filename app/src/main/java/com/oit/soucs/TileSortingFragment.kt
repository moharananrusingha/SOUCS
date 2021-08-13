package com.oit.soucs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.oit.soucs.adapters.TilesAdapter
import com.oit.soucs.data.Tile
import com.oit.soucs.databinding.FragmentTileSortingBinding
import com.oit.soucs.util.RecyclerViewDragDetector
import com.oit.soucs.viewmodels.TileListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TileSortingFragment : Fragment() {

    private lateinit var binding: FragmentTileSortingBinding
    private val viewModel: TileListViewModel by viewModels()
    private lateinit var adapter: TilesAdapter
    private var tileList: MutableList<Tile> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTileSortingBinding.inflate(inflater,container,false)
        ItemTouchHelper(object : RecyclerViewDragDetector() {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                tileList[viewHolder.adapterPosition] = tileList[target.adapterPosition].also {
                    tileList[target.adapterPosition] = tileList[viewHolder.adapterPosition] }
                tileList[viewHolder.adapterPosition].sortId = tileList[target.adapterPosition].also {
                    tileList[target.adapterPosition].sortId = tileList[viewHolder.adapterPosition].sortId }.sortId
                adapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)

                return true
            }
            override fun isLongPressDragEnabled(): Boolean {
                return true
            }
        }).attachToRecyclerView(binding.tilesRcv)
        adapter = TilesAdapter()
        binding.tilesRcv.adapter = adapter
        viewModel.tiles.observe(viewLifecycleOwner){
            it?.let { tileList = it as MutableList<Tile>
                adapter.submitList(it)
            }
        }
        binding.btnShowResult.setOnClickListener {
            val grade = (0..4).random()
            val action = TileSortingFragmentDirections.actionTileSortingFragmentToResultFragment(grade)
            it.findNavController().navigate(action)
        }
        return binding.root
    }
}