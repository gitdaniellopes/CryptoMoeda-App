package br.com.criptomoedaapp.domain.use_case.get_coin

import br.com.criptomoedaapp.common.NetworkResponse
import br.com.criptomoedaapp.data.remote.response.toCoinDetail
import br.com.criptomoedaapp.domain.model.CoinDetail
import br.com.criptomoedaapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<NetworkResponse<CoinDetail>> = flow {
        try {
            emit(NetworkResponse.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(NetworkResponse.Success(coin))
        } catch (e: HttpException) {
            emit(NetworkResponse.Error(e.localizedMessage ?: "Um erro inesperado aconteceu"))
        } catch (e: IOException) {
            emit(NetworkResponse.Error("Problemas no servidor. Verifique a conexão com a internet"))
        }
    }
}