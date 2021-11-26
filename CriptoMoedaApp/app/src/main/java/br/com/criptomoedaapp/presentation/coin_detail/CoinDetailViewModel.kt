package br.com.criptomoedaapp.presentation.coin_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.criptomoedaapp.common.Constants.PARAM_COIN_ID
import br.com.criptomoedaapp.common.NetworkResponse
import br.com.criptomoedaapp.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * savedStateHandle - um identificador de estado
 * basicamente é um pacote que conten as informações sobre o estado salvo para que
 * possamos usar isso e restaurar nosso aplicativo quando ele "morre" do processo
 *
 * o id vai estar contido nesse estado de instancia salvo e então passamos ele apenas para o nosso viewModel
 *
 * No fragmento que recebe esse arqgumento, eu preciso apenas colocar:
 * exemplo: private val args: CoinDetailFragmentArgs by navArgs() - args.coinId
 * lembrando que o parametro deve ser exatamente igual.
 * */

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val useCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(CoinDetailState())
    val state: StateFlow<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
            getCoins(coinId)
        }
    }

    private fun getCoins(coinId: String) {
        useCase(coinId).onEach { result ->
            when (result) {
                is NetworkResponse.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is NetworkResponse.Error -> {
                    _state.value = CoinDetailState(error = result.message ?: "Um erro ocorreu")
                }
                is NetworkResponse.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}