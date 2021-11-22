package br.com.criptomoedaapp.data.remote

import br.com.criptomoedaapp.data.remote.response.CoinDetailResponse
import br.com.criptomoedaapp.data.remote.response.CoinResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinResponse>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailResponse
}