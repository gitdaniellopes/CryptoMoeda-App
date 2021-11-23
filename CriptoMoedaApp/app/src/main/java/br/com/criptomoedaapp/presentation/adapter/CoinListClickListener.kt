package br.com.criptomoedaapp.presentation.adapter

class CoinListClickListener(val clickListener: (coinId: String) -> Unit) {
    fun onClick(coinId: String) = clickListener(coinId)
}
