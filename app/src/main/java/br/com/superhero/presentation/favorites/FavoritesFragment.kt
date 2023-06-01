package br.com.superhero.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.superhero.databinding.FragmentFavoritesBinding
import br.com.superhero.framework.imageloader.ImageLoader
import br.com.superhero.presentation.common.getGenericAdapterOf
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding: FragmentFavoritesBinding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    private val favoriteAdapter by lazy {
        getGenericAdapterOf {
            FavoriteViewHolder.create(it, imageLoader)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentFavoritesBinding.inflate(inflater, container, false)
        .apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFavoritesAdapter()

        viewModel.state.observe(viewLifecycleOwner) { uiState ->
            binding.flipperFavorite.displayedChild = when (uiState) {
                is FavoriteViewModel.UiState.ShowFavorite -> {
                    favoriteAdapter.submitList(uiState.favorite)
                    FLIPPER_CHILD_CHARACTERS
                }
                FavoriteViewModel.UiState.ShowEmpty -> {
                    favoriteAdapter.submitList(emptyList())
                    FLIPPER_CHILD_EMPTY
                }
            }
        }
        viewModel.getAll()
    }

    private fun initFavoritesAdapter() {
        binding.rvFavorites.run {
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FLIPPER_CHILD_CHARACTERS = 0
        private const val FLIPPER_CHILD_EMPTY = 1
    }
}