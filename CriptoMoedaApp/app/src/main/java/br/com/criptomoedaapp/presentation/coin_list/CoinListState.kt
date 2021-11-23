package br.com.criptomoedaapp.presentation.coin_list

import br.com.criptomoedaapp.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)