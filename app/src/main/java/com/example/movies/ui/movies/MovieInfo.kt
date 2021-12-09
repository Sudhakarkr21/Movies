package com.example.movies.ui.movies

import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.example.movies.R
import com.example.movies.base.BaseFragment
import com.example.movies.databinding.MovieInfoFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieInfo : BaseFragment<MovieInfoFragmentBinding,MovieInfoViewModel>(){

    private val movieInfoArgs : MovieInfoArgs by navArgs()
    private val movieInfoViewModel : MovieInfoViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.movie_info_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun getVM(): MovieInfoViewModel = movieInfoViewModel

    override fun bindVM(binding: MovieInfoFragmentBinding, vm: MovieInfoViewModel) {
        with(binding) {
            tvshow = movieInfoArgs.tvshow
            ViewCompat.setTransitionName(binding.imageMovie, "avatar_${movieInfoArgs.tvshow.id}")
        }
    }
}