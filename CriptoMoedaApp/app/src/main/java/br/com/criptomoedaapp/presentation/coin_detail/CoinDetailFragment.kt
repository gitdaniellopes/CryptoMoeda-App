package br.com.criptomoedaapp.presentation.coin_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import br.com.criptomoedaapp.databinding.FragmentDetailCoinBinding
import br.com.criptomoedaapp.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CoinDetailFragment : BaseFragment<FragmentDetailCoinBinding>() {

    private val args: CoinDetailFragmentArgs by navArgs()
    private val viewModel: CoinDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * A biblioteca Safe Args passa seu conteúdo de forma que fique automaticamente disponível no
         * SavedStateHandle que o Hilt fornece para você. args.coinId
         * */
        args.coinId
        observerCollect()
    }

    private fun observerCollect() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when {
                    state.error.isNotBlank() -> {
                        Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.INVISIBLE
                    }
                    state.isLoading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.progressBar.visibility = View.INVISIBLE
                    }
                }
                binding.coinDetail = state.coin

            }
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailCoinBinding = FragmentDetailCoinBinding.inflate(inflater, container, false)
}