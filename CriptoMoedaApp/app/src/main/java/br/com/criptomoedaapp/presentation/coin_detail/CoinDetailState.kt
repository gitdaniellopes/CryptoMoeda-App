package br.com.criptomoedaapp.presentation.coin_detail

import br.com.criptomoedaapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)