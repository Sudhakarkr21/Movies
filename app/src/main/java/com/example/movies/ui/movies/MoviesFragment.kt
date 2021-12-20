package com.example.movies.ui.movies

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.movies.R
import com.example.movies.adapters.ImageSliderAdapter
import com.example.movies.adapters.TvListAdapter
import com.example.movies.base.BaseFragment
import com.example.movies.databinding.ItemMoviesBinding
import com.example.movies.databinding.MoviesFragmentBinding
import com.example.movies.model.TvShows
import com.example.movies.util.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment : BaseFragment<MoviesFragmentBinding, MoviesViewModel>(),
    TvListAdapter.CharacterClickListener {

    private val viewModel: MoviesViewModel by viewModels()

    @Inject
    lateinit var tvListAdapter: TvListAdapter

    @Inject
    lateinit var imageSliderAdapter: ImageSliderAdapter

    override val layoutId: Int
        get() = R.layout.movies_fragment

    override fun getVM(): MoviesViewModel = viewModel

    override fun bindVM(binding: MoviesFragmentBinding, vm: MoviesViewModel) =
        with(binding) {
            with(mainViewPager) {
                adapter = imageSliderAdapter
                clipToPadding = false
                clipChildren = false
                offscreenPageLimit = 3
                getChildAt(0).let {
                    overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                }
                setPageTransformer(composite())
            }

            with(tvListAdapter) {
                binding.rvMovies.apply {
                    postponeEnterTransition()
                    viewTreeObserver.addOnPreDrawListener {
                        startPostponedEnterTransition()
                        true
                    }
                }
                binding.rvMovies.adapter = withLoadStateHeaderAndFooter(
                    header = PagingLoadStateAdapter(this),
                    footer = PagingLoadStateAdapter(this)
                )

               // swipeRefresh.setOnRefreshListener { refresh() }
                characterClickListener = this@MoviesFragment

                with(vm) {

                    vm.getImageSlideData().value?.let {
                        imageSliderAdapter.submitList(it,binding.mainViewPager)
                        imageSliderAdapter.notifyDataSetChanged()
                    }
                    launchOnLifecycleScope {
                        charactersFlow.collectLatest { submitData(it) }
                    }
                    /*launchOnLifecycleScope {
                        loadStateFlow.collectLatest {
                            swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                        }
                    }*/


                }
            }
        }

    /* override fun onCharacterClicked(binding: ItemMoviesBinding, character: TvShows) {
         val extras = FragmentNavigatorExtras(
             binding.ivAvatar to "avatar_${character.id}",
         )
         findNavController().navigate(
             CharactersFragmentDirections.actionCharactersToCharacterDetail(character),
             extras
         )
     }*/

    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.movies_fragment, container, false)
        if (bindingMoviesFragment == null) {
            bindingMoviesFragment = MoviesFragmentBinding.bind(root)
        }

        with(bindingMoviesFragment){
            with(tvListAdapter){
                bindingMoviesFragment!!.rvMovies.apply {
                    postponeEnterTransition()
                    viewTreeObserver.addOnPreDrawListener {
                        startPostponedEnterTransition()
                        true
                    }
                }

                bindingMoviesFragment!!.rvMovies.adapter =  withLoadStateHeaderAndFooter(
                    header = PagingLoadStateAdapter(this),
                    footer = PagingLoadStateAdapter(this)
                )

                bindingMoviesFragment!!.swipeRefresh.setOnRefreshListener { refresh() }
                characterClickListener = this@CharactersFragment

            }

            with(viewModel) {
                launchOnLifecycleScope {
                    charactersFlow.collectLatest { submitData(it) }
                }
                launchOnLifecycleScope {
                    loadStateFlow.collectLatest {
                        swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                    }
                }
            }
        }

        return bindingMoviesFragment!!.root
    }
*/

    override fun onCharacterClicked(binding: ItemMoviesBinding, character: TvShows) {
        val extras = FragmentNavigatorExtras(
            binding.ivAvatar to "avatar_${character.id}",
        )
        findNavController().navigate(
            MoviesFragmentDirections.actionMoviesFragmentToMovieInfo(tvshow = character),
            extras
        )
    }

    fun composite() = CompositePageTransformer().apply {
        addTransformer(MarginPageTransformer(40))
        addTransformer(ViewPager2.PageTransformer { page, position ->
            run {
                val r = 1 - Math.abs(position)
                page.scaleY = r
            }
        })
    }



}