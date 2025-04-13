package yong.chul.rank.user_ranking_app.intrastructure.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class UserScoreRandUpdateService {

    @Scheduled(cron = "0 0/5 * * * *")
    fun updateUserRank() {

    }
}