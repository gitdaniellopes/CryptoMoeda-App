package br.com.criptomoedaapp.presentation.adapter.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import br.com.criptomoedaapp.R
import br.com.criptomoedaapp.domain.model.Coin
import com.google.android.material.chip.Chip

@BindingAdapter("coinRankTv")
fun TextView.setCoinRank(coin: Coin?) {
    coin?.let {
        text = it.rank.toString()
    }
}

@BindingAdapter("coinNameTv")
fun TextView.setCoinName(coin: Coin?) {
    coin?.let {
        text = it.name
    }
}

@BindingAdapter("coinSymbolTv")
fun TextView.setCoinSymbol(coin: Coin?) {
    coin?.let {
        text = "(${it.symbol})"
    }
}

@BindingAdapter("coinActiveTv")
fun Chip.setCoinActive(coin: Coin?) {
    coin?.let {
        if (it.isActive) {
            text = context.getString(R.string.active)
            setChipBackgroundColorResource(R.color.green)
        } else {
            text = context.getString(R.string.Inactive)
            setChipBackgroundColorResource(R.color.red)
        }
    }
}