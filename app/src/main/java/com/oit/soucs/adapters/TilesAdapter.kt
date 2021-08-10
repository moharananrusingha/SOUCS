package com.oit.soucs.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oit.soucs.data.Tile
import com.oit.soucs.databinding.TilesItemViewBinding

class TilesAdapter : ListAdapter<Tile,TilesAdapter.MenuItemViewHolder>(TilesDiffCallback()){

    inner class MenuItemViewHolder(private val tilesItemViewBinding: TilesItemViewBinding):
        RecyclerView.ViewHolder(tilesItemViewBinding.root){
        fun bind(tile: Tile){
            tilesItemViewBinding.tileItemImage.scaleType = ImageView.ScaleType.CENTER_CROP
            tilesItemViewBinding.tileItemName.text = tile.name
            tilesItemViewBinding.tileItemDesc.text = tile.description
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder =
        MenuItemViewHolder(
            TilesItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) = holder.bind(getItem(position))

}
private class TilesDiffCallback: DiffUtil.ItemCallback<Tile>() {
    override fun areItemsTheSame(oldItem: Tile, newItem: Tile): Boolean {
        return oldItem.tileId == newItem.tileId
    }

    override fun areContentsTheSame(oldItem: Tile, newItem: Tile): Boolean {
        return oldItem == newItem
    }

}