package br.com.criptomoedaapp.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.criptomoedaapp.common.NetworkResponse
import br.com.criptomoedaapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val useCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state: StateFlow<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        useCase().onEach { result ->
            when (result) {
                is NetworkResponse.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is NetworkResponse.Error -> {
                    _state.value = CoinListState(error = result.message ?: "Um erro ocorreu")
                }
                is NetworkResponse.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}