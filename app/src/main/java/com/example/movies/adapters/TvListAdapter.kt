package com.example.movies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ItemMoviesBinding
import com.example.movies.model.MovieModel
import com.example.movies.model.TvShows
import javax.inject.Inject

class TvListAdapter @Inject constructor() :
    PagingDataAdapter<MovieModel, TvListAdapter.TvListViewHolder>(movieComparator) {
    var characterClickListener: CharacterClickListener? = null

    inner class TvListViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                characterClickListener?.onCharacterClicked(
                    binding,
                    getItem(absoluteAdapterPosition)?.tvShows?.get(absoluteAdapterPosition) as TvShows
                )
            }
        }

        fun bind(item: TvShows) = with(binding) {
            ViewCompat.setTransitionName(binding.ivAvatar, "avatar_${item.id}")
            tvshow = item
        }
    }

    interface CharacterClickListener {
        fun onCharacterClicked(binding: ItemMoviesBinding, character: TvShows)
    }

    object tvListComparator : DiffUtil.ItemCallback<TvShows>() {
        override fun areItemsTheSame(oldItem: TvShows, newItem: TvShows) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TvShows, newItem: TvShows): Boolean {
            return oldItem == newItem
        }

    }

    object movieComparator : DiffUtil.ItemCallback<MovieModel>(){
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.page == newItem.page
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: TvListViewHolder, position: Int) {
        try {
            getItem(position)?.let { holder.bind(it.tvShows[position]) }

        }catch (e : Exception){
            e.localizedMessage
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvListViewHolder {
        return TvListViewHolder(
            ItemMoviesBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }
}