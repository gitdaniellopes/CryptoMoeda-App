package br.com.criptomoedaapp.domain.repository

import br.com.criptomoedaapp.data.remote.response.CoinDetailResponse
import br.com.criptomoedaapp.data.remote.response.CoinResponse

interface CoinRepository {

    suspend fun getCoins(): List<CoinResponse>
    suspend fun getCoinById(coinId: String): CoinDetailResponse
}