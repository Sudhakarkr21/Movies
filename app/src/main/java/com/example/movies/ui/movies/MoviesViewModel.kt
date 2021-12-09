package com.example.movies.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movies.base.BaseViewModel
import com.example.movies.model.ImageSliderModel
import com.example.movies.model.MovieModel
import com.example.movies.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : BaseViewModel() {
    private lateinit var _charactersFlow: Flow<PagingData<MovieModel>>
    private var mutableLiveData : MutableLiveData<List<ImageSliderModel>>? = null
    val charactersFlow: Flow<PagingData<MovieModel>>
        get() = _charactersFlow

    init {
        getAllCharacters()
    }

    public fun getImageSlideData() : LiveData<List<ImageSliderModel>>{
        if (mutableLiveData == null){
            mutableLiveData = MutableLiveData()
            loadImageSlide()
        }

        return mutableLiveData as MutableLiveData<List<ImageSliderModel>>
    }

    fun loadImageSlide(){
        var arrayList : ArrayList<ImageSliderModel> = ArrayList()
        arrayList.add(ImageSliderModel("https://static.episodate.com/images/tv-show/thumbnail/35624.jpg",1))
        arrayList.add(ImageSliderModel("https://static.episodate.com/images/tv-show/thumbnail/23455.jpg",1))
        arrayList.add(ImageSliderModel("https://static.episodate.com/images/tv-show/thumbnail/43467.com",1))
        arrayList.add(ImageSliderModel("https://static.episodate.com/images/tv-show/thumbnail/43234.jpg",1))
        arrayList.add(ImageSliderModel("https://static.episodate.com/images/tv-show/thumbnail/46692.jpg",1))
        mutableLiveData?.value = arrayList
    }

    private fun getAllCharacters() = launchPagingAsync({
        repository.getAllCharacters().cachedIn(viewModelScope)
    }, {
        _charactersFlow = it
    })
}