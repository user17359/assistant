package com.example.assistant.repositories

import com.example.assistant.data.Achievement
import com.example.assistant.data.presets.setOfAchievements

interface AchievementRepository {
    suspend fun getAchievements(): List<Achievement>
    suspend fun updateAchievement(position: Int, achievement: Achievement)
}

class LocalAchievementRepository : AchievementRepository {

    private val mutableList = setOfAchievements.toMutableList()

    override suspend fun getAchievements(): List<Achievement> {
        return mutableList
    }

    override suspend fun updateAchievement(position: Int, achievement: Achievement) {
        mutableList[position].let {
            it.requiredNumber = achievement.requiredNumber
            it.currentNumber = achievement.currentNumber
            it.tier = achievement.tier
        }
    }

}