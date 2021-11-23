package br.com.criptomoedaapp.presentation.coin_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.criptomoedaapp.databinding.FragmentListCoinBinding
import br.com.criptomoedaapp.presentation.adapter.CoinAdapter
import br.com.criptomoedaapp.presentation.adapter.CoinListClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CoinListFragment : Fragment() {

    private var _binding: FragmentListCoinBinding? = null
    private val binding: FragmentListCoinBinding get() = _binding!!

    private val viewModel: CoinListViewModel by viewModels()
    private val coinAdapter: CoinAdapter by lazy {
        CoinAdapter(CoinListClickListener { coinId ->
            val action = CoinListFragmentDirections
                .actionCoinListFragmentToCoinDetailFragment(coinId)
            findNavController().navigate(action)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        initCollect()
    }

    private fun setupRecycleView() {
        binding.rvCoins.apply {
            adapter = coinAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun initCollect() {
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
                coinAdapter.coins = state.coins.toList()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListCoinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}