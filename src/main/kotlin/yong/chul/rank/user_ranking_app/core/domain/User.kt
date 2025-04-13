package yong.chul.rank.user_ranking_app.core.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "user")
data class User(
    @Id
    @Column(name = "user_id", length = 50)
    var userId: String,
    @Column(name = "nickname", length = 30)
    var nickname: String,
    @Column(name = "profile_image_url", length = 1000)
    var profileImageUrl: String
): BaseEntity(){
    var totalScore: Long = 0
    var lastPlayedAt: LocalDateTime? = null

    fun updateTotalScore(totalScore: Long){
        this.totalScore += totalScore
        this.lastPlayedAt = LocalDateTime.now()
    }
}
