package yong.chul.rank.user_ranking_app.core.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import yong.chul.rank.user_ranking_app.core.domain.User

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun findByUserId(userId: String): User?
}