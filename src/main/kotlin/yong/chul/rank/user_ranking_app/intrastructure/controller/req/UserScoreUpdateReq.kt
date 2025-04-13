package yong.chul.rank.user_ranking_app.intrastructure.controller.req

import yong.chul.rank.user_ranking_app.usecase.command.UserScoreUpdateCommand

data class UserScoreUpdateReq(
    val userId: String,
    val nickname: String,
    val profileImageUrl: String,
    val score: Long
){
    fun toCommand(): UserScoreUpdateCommand{
        return UserScoreUpdateCommand(this.userId, this.nickname, this.profileImageUrl, this.score)
    }
}

