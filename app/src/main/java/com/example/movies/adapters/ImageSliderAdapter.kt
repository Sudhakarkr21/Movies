package com.example.movies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.movies.databinding.ItemMoviesBinding
import com.example.movies.databinding.RoundedSlideContainerBinding
import com.example.movies.model.ImageSliderModel
import com.example.movies.model.MovieModel
import com.example.movies.model.TvShows
import javax.inject.Inject

class ImageSliderAdapter @Inject constructor()
    : RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {


    val differ = AsyncListDiffer(this, imageComparator)
    var viewPager2:ViewPager2? = null
    fun submitList(list: List<ImageSliderModel>,viewPager2: ViewPager2){
        differ.submitList(list)
        this.viewPager2 = viewPager2
    }

    inner class ImageSliderViewHolder(private val binding: RoundedSlideContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(imageSliderModel: ImageSliderModel){
                binding.image = imageSliderModel
            }
    }

    object imageComparator : DiffUtil.ItemCallback<ImageSliderModel>(){
        override fun areItemsTheSame(oldItem: ImageSliderModel, newItem: ImageSliderModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageSliderModel, newItem: ImageSliderModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {

            return ImageSliderViewHolder(
                RoundedSlideContainerBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size
}