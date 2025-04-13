package yong.chul.rank.user_ranking_app.usecase.command

import yong.chul.rank.user_ranking_app.core.domain.User
import java.time.LocalDateTime

data class UserScoreUpdateCommand(
    val userId: String,
    val nickname: String,
    val profileImageUrl: String,
    val score: Long
){
    fun toDomain(): User{
        return User(this.userId, this.nickname, profileImageUrl)
    }
}
