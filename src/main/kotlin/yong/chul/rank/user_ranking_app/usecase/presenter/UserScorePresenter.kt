package yong.chul.rank.user_ranking_app.usecase.presenter

import yong.chul.rank.user_ranking_app.core.domain.User
import yong.chul.rank.user_ranking_app.utils.fromFormatter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class UserScorePresenter(
    val rank: Int,
    val nickname: String,
    val profileImageUrl: String,
    val totalScore: Long,
    val lastPlayedAt: String
) {
    constructor(user: User, rank: Int): this(
        rank = rank,
        nickname = user.nickname,
        profileImageUrl = user.profileImageUrl,
        totalScore = user.totalScore,
        lastPlayedAt = user.lastPlayedAt?.fromFormatter() ?: LocalDateTime.now().fromFormatter()
    )
}


