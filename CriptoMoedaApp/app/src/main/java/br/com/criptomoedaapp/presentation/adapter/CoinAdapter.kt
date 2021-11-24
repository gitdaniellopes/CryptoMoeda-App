package br.com.criptomoedaapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.criptomoedaapp.databinding.ItemCoinBinding
import br.com.criptomoedaapp.domain.model.Coin

class CoinAdapter(
    private val clickListener: CoinListClickListener
) : ListAdapter<Coin, CoinAdapter.CoinViewHolder>(CoinDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        return CoinViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

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
            binding.executePendingBindings()
        }
    }

    class CoinDiffUtilCallback : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }
    }
}