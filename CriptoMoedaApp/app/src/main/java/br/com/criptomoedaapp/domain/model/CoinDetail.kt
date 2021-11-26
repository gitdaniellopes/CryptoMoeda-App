package br.com.criptomoedaapp.domain.model

import br.com.criptomoedaapp.data.remote.response.TeamMember

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>? = null,
    val team: List<TeamMember>
){
    fun getTagCount() = tags?.size
    fun getTeamCount() = team.size
}