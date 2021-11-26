package br.com.criptomoedaapp.presentation.adapter.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import br.com.criptomoedaapp.R
import br.com.criptomoedaapp.domain.model.CoinDetail
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("coinRankTv")
fun TextView.setCoinRank(coin: CoinDetail?) {
    coin?.let {
        text = it.rank.toString()
    }
}

@BindingAdapter("coinTotalMembers")
fun TextView.setTotalMembers(coin: CoinDetail?) {
    coin?.let {
        text = it.getTeamCount().toString()
    }
}

@BindingAdapter("coinActiveDetailTv")
fun Chip.setCoinActiveDetail(coin: CoinDetail?) {
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

@BindingAdapter("coinSymbolTvDetail")
fun TextView.setCoinSymbolDetail(coin: CoinDetail?) {
    coin?.let {
        text = "(${it.symbol})"
    }
}

@BindingAdapter("coinTags")
fun ChipGroup.setTagsCoin(coin: CoinDetail?) {
    coin?.let { coinDetail ->
        coinDetail.tags?.let { list ->
            for (tags in list) {
                val chip = Chip(context)
                chip.text = tags
                addView(chip)
            }
        }
    }
}