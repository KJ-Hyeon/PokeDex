package com.example.pokemonmaster.ui.home

import android.os.Build.VERSION.SDK_INT
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import coil.request.Disposable
import coil.request.ImageRequest
import com.example.pokemonmaster.databinding.ItemPokemonBinding
import com.example.pokemonmaster.domain.entity.PokemonEntity

class HomeAdapter: ListAdapter<HomePokeItem, HomeAdapter.HomeViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HomeViewHolder(ItemPokemonBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HomeViewHolder(private val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomePokeItem) {
            Log.d("HomeAdapter:","HomeAdapter:$item")
            with(binding) {
                tvName.text = item.name
                tvNumber.text = "# ${item.number}"
                tvGenera.text = item.genera
                setImageGif(item)
            }
        }

        private fun ItemPokemonBinding.setImageGif(item: HomePokeItem): Disposable {
            val imageLoader = ImageLoader.Builder(ivPokemon.context)
                .components {
                    if (SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }.build()
            val request = ImageRequest.Builder(ivPokemon.context)
                .data(item.image)
                .target(ivPokemon)
                .build()
            return imageLoader.enqueue(request)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<HomePokeItem>() {
            override fun areItemsTheSame(oldItem: HomePokeItem, newItem: HomePokeItem): Boolean {
                return oldItem.name == newItem.number
            }

            override fun areContentsTheSame(
                oldItem: HomePokeItem, newItem: HomePokeItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}