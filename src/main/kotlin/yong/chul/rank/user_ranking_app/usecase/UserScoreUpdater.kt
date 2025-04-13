package yong.chul.rank.user_ranking_app.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import yong.chul.rank.user_ranking_app.core.domain.UserScore
import yong.chul.rank.user_ranking_app.core.repository.UserRepository
import yong.chul.rank.user_ranking_app.core.repository.UserScoreRepository
import yong.chul.rank.user_ranking_app.intrastructure.redis.UserScoreRedisService
import yong.chul.rank.user_ranking_app.usecase.command.UserScoreUpdateCommand

@Service
class UserScoreUpdater(
    private val userRepository: UserRepository,
    private val userScoreRepository: UserScoreRepository,
    private val userScoreRedisService: UserScoreRedisService
) {

    @Transactional
    fun userScoreUpdate(command: UserScoreUpdateCommand){
        val user = userRepository.findByUserId(command.userId) ?: userRepository.save(command.toDomain())
        val newScore = UserScore(command.score, user)
        user.updateTotalScore(command.score)
        userScoreRepository.save(newScore)
        userScoreRedisService.addUserScore(user)
    }
}