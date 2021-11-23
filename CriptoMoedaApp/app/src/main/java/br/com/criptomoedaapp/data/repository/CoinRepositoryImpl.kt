package br.com.criptomoedaapp.data.repository

import br.com.criptomoedaapp.data.remote.CoinPaprikaApi
import br.com.criptomoedaapp.data.remote.response.CoinDetailResponse
import br.com.criptomoedaapp.data.remote.response.CoinResponse
import br.com.criptomoedaapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinResponse> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailResponse {
        return api.getCoinById(coinId)
    }
}