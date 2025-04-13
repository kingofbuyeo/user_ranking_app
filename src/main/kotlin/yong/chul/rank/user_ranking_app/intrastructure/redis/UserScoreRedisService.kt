package yong.chul.rank.user_ranking_app.intrastructure.redis

import org.springframework.data.redis.core.DefaultTypedTuple
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ZSetOperations
import org.springframework.stereotype.Service
import yong.chul.rank.user_ranking_app.core.domain.User
import yong.chul.rank.user_ranking_app.utils.fromJson
import yong.chul.rank.user_ranking_app.utils.toJsonString

@Service
class UserScoreRedisService(
    private val redisTemplate: RedisTemplate<String, String>
) {
    private val zSetOps: ZSetOperations<String, String> = redisTemplate.opsForZSet()

    private val key = "user_ranking"
    private val userKey = "user:detail:"

    // 데이터 추가
    fun addUserScore(user: User) {
        val timeNano = (10_000_000_000_000 - System.currentTimeMillis()).toDouble().div(100000000000000)
        zSetOps.add(key, user.userId, user.totalScore.toDouble() + timeNano)
        saveUserObject(user)
    }

    // 순위 조회 (높은 점수 순)
    fun getTopUsers(page: Long, limit: Long): Set<ZSetOperations.TypedTuple<String>>? {
        return zSetOps.reverseRangeWithScores(key, page , limit - 1)
    }

    fun saveUserObject(user: User) {
        redisTemplate.opsForValue().set("$userKey${user.userId}", user.toJsonString())
    }

    fun findUserList(userIds: Set<ZSetOperations.TypedTuple<String>>): List<User>{
        return userIds.mapNotNull { tuple ->
            val json = redisTemplate.opsForValue().get("$userKey${tuple.value}")
            json?.fromJson<User>()
        }
    }

    // 특정 유저 점수 가져오기
    fun getUserScore(userId: String): Long? {
        return zSetOps.score(key, userId)?.toLong()
    }

    // 특정 유저의 순위 가져오기 (0부터 시작)
    fun getUserRank(userId: String): Long? {
        return zSetOps.reverseRank(key, userId)
    }

    // 여러 사용자와 점수를 한번에 추가
    fun addMultipleUserScores(userScores: Map<String, Double>) {
        val tuples = userScores.map { (userId, score) ->
            DefaultTypedTuple(userId, score)
        }.toSet()

        zSetOps.add(key, tuples)
    }
}