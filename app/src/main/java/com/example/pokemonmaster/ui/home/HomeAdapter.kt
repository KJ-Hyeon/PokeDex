package com.example.pokemonmaster.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokemonmaster.databinding.ItemPokemonBinding
import com.example.pokemonmaster.domain.entity.PokemonEntity

class HomeAdapter: ListAdapter<PokemonEntity, HomeAdapter.HomeViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HomeViewHolder(ItemPokemonBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HomeViewHolder(private val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PokemonEntity) {
            with(binding) {
                tvName.text = item.name
                tvNumber.text = "# ${item.number}"
                tvGenera.text = item.genera
                ivPokemon.load(item.image)
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PokemonEntity>() {
            override fun areItemsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
                return oldItem.name == newItem.number
            }

            override fun areContentsTheSame(
                oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}