package yong.chul.rank.user_ranking_app.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import yong.chul.rank.user_ranking_app.intrastructure.redis.UserScoreRedisService
import yong.chul.rank.user_ranking_app.usecase.exception.RankingNotFoundException
import yong.chul.rank.user_ranking_app.usecase.presenter.UserScorePresenter

@Service
class SearchUserRanking(
    private val userScoreRedisService: UserScoreRedisService
) {

    @Transactional(readOnly = true)
    fun searchRanking(): List<UserScorePresenter> {
        val userIds = userScoreRedisService.getTopUsers(0, 10) ?: throw RankingNotFoundException()
        val users = userScoreRedisService.findUserList(userIds)
        return userIds.mapIndexed { index, typedTuple ->
            val rank = index + 1
            val user = users.find { it.userId == typedTuple.value } ?: return@mapIndexed null
            UserScorePresenter(
                user,
                rank
            )
        }.filterNotNull()
    }
}