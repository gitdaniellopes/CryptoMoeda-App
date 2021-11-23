package br.com.criptomoedaapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.criptomoedaapp.databinding.ItemCoinBinding
import br.com.criptomoedaapp.domain.model.Coin

class CoinAdapter(
    private val clickListener: CoinListClickListener
) : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        return CoinViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(coins[position], clickListener)
    }

    override fun getItemCount(): Int = coins.size

    class CoinViewHolder(private val binding: ItemCoinBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): CoinViewHolder {
                val binding = ItemCoinBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return CoinViewHolder(binding)
            }
        }

        fun bind(item: Coin, clickListener: CoinListClickListener) {
            binding.coin = item
            binding.clickListener = clickListener
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    var coins: List<Coin>
        get() = differ.currentList
        set(value) = differ.submitList(value)
}