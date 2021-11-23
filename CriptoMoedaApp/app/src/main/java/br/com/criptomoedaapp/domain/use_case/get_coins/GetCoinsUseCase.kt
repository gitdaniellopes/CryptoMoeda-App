package br.com.criptomoedaapp.domain.use_case.get_coins

import br.com.criptomoedaapp.common.NetworkResponse
import br.com.criptomoedaapp.data.remote.response.toCoin
import br.com.criptomoedaapp.domain.model.Coin
import br.com.criptomoedaapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<NetworkResponse<List<Coin>>> = flow {
        try {
            emit(NetworkResponse.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(NetworkResponse.Success(coins))
        } catch (e: HttpException) {
            emit(NetworkResponse.Error(e.localizedMessage ?: "Um erro inesperado aconteceu"))
        } catch (e: IOException) {
            emit(NetworkResponse.Error("Problemas no servidor. Verifique a conexão com a internet"))
        }
    }
}