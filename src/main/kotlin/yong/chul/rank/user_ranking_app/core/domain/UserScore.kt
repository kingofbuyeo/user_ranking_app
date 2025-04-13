package yong.chul.rank.user_ranking_app.core.domain

import jakarta.persistence.*

@Entity
@Table(name = "user_score")
data class UserScore (
    val score: Long,
    @ManyToOne(fetch = FetchType.LAZY)
    val user: User
): BaseEntity(){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}