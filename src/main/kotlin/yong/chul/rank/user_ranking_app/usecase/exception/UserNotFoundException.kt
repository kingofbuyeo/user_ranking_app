package yong.chul.rank.user_ranking_app.usecase.exception

class UserNotFoundException(userId: String): RuntimeException("User not found: $userId") {
}