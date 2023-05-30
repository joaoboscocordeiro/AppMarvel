package br.com.superhero.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import br.com.superhero.databinding.FragmentDetailBinding
import br.com.superhero.framework.imageloader.ImageLoader
import br.com.superhero.presentation.extensions.showLongToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailBinding.inflate(inflater, container, false)
        .apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailViewArg = args.detailViewArg
        binding.imageCharacter.run {
            transitionName = detailViewArg.name
            imageLoader.load(this, detailViewArg.imageUrl)
        }

        setSharedElementTransitionOnEnter()
        loadCategoriesAndObserverUiState(detailViewArg)
        setAndObserverFavoriteUiState(detailViewArg)
    }

    private fun loadCategoriesAndObserverUiState(detailViewArg: DetailViewArg) {
        viewModel.categories.load(detailViewArg.characterId)
        viewModel.categories.state.observe(viewLifecycleOwner) { uiState ->
            binding.flipperDetail.displayedChild = when (uiState) {
                UiActionsStateLiveData.UiState.Loading -> FLIPPER_CHILD_POSITION_LOADING
                is UiActionsStateLiveData.UiState.Success -> {
                    binding.recyclerParentDetail.run {
                        setHasFixedSize(true)
                        adapter = DetailParentAdapter(uiState.detailParentList, imageLoader)
                    }
                    FLIPPER_CHILD_POSITION_DETAIL
                }
                UiActionsStateLiveData.UiState.Error -> {
                    binding.includeErrorView.buttonRetry.setOnClickListener {
                        viewModel.categories.load(detailViewArg.characterId)
                    }
                    FLIPPER_CHILD_POSITION_ERROR
                }
                UiActionsStateLiveData.UiState.Empty -> FLIPPER_CHILD_POSITION_EMPTY
            }
        }
    }

    private fun setAndObserverFavoriteUiState(detailViewArg: DetailViewArg) {
        binding.imageFavoriteIcon.setOnClickListener {
            viewModel.favorite.update(detailViewArg)
        }

        viewModel.favorite.state.observe(viewLifecycleOwner) { uiState ->
            binding.flipperFavorite.displayedChild = when (uiState) {
                FavoriteUiActionStateLiveData.UiState.Loading -> FLIPPER_FAVORITE_CHILD_POSITION_LOADING
                is FavoriteUiActionStateLiveData.UiState.Icon -> {
                    binding.imageFavoriteIcon.setImageResource(uiState.icon)
                    FLIPPER_FAVORITE_CHILD_POSITION_IMAGE
                }
                is FavoriteUiActionStateLiveData.UiState.Error -> {
                    showLongToast(uiState.messageResId)
                    FLIPPER_FAVORITE_CHILD_POSITION_IMAGE
                }
            }
        }
    }

    // Define a animação da transição como "move"
    private fun setSharedElementTransitionOnEnter() {
        TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move).apply {
                sharedElementEnterTransition = this
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FLIPPER_CHILD_POSITION_LOADING = 0
        private const val FLIPPER_CHILD_POSITION_DETAIL = 1
        private const val FLIPPER_CHILD_POSITION_ERROR = 2
        private const val FLIPPER_CHILD_POSITION_EMPTY = 3
        private const val FLIPPER_FAVORITE_CHILD_POSITION_IMAGE = 0
        private const val FLIPPER_FAVORITE_CHILD_POSITION_LOADING = 1
    }
}