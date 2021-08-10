package com.oit.soucs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.oit.soucs.adapters.TilesAdapter
import com.oit.soucs.data.Tile
import com.oit.soucs.data.TileRepository
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
                tileList[viewHolder.bindingAdapterPosition] = tileList[target.bindingAdapterPosition].also {
                    tileList[target.bindingAdapterPosition] = tileList[viewHolder.bindingAdapterPosition] }
                tileList[viewHolder.bindingAdapterPosition].sortId = tileList[target.bindingAdapterPosition].also {
                    tileList[target.bindingAdapterPosition].sortId = tileList[viewHolder.bindingAdapterPosition].sortId }.sortId
                adapter.notifyItemMoved(viewHolder.bindingAdapterPosition, target.bindingAdapterPosition)

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
        return binding.root
    }
    fun onClickShowResult(v: View){

    }
}