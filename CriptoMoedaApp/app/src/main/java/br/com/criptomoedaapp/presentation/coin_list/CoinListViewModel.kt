package br.com.criptomoedaapp.presentation.coin_list

import androidx.lifecycle.ViewModel
import br.com.criptomoedaapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val useCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CoinListState>()


}