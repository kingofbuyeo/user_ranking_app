package yong.chul.rank.user_ranking_app.intrastructure.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import yong.chul.rank.user_ranking_app.intrastructure.controller.req.UserScoreUpdateReq
import yong.chul.rank.user_ranking_app.usecase.SearchUserRanking
import yong.chul.rank.user_ranking_app.usecase.UserScoreUpdater
import yong.chul.rank.user_ranking_app.usecase.presenter.UserScorePresenter

@RestController
class UserScoreController(
    private val userScoreUpdater: UserScoreUpdater,
    private val searchUserRanking: SearchUserRanking
) {

    @PostMapping("/game/scores")
    fun updateScore(
        @RequestBody req: UserScoreUpdateReq
    ){
        userScoreUpdater.userScoreUpdate(req.toCommand())
    }

    @GetMapping("/game-rankings/top10")
    fun searchRanking(): List<UserScorePresenter> {
        return searchUserRanking.searchRanking()
    }
}