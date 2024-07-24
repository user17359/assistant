package com.example.assistant.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assistant.data.Achievement
import com.example.assistant.repositories.LocalAchievementRepository

class AchievementViewModel (
    private val achievementRepository: LocalAchievementRepository = LocalAchievementRepository()
): ViewModel() {

    val achievementList = MutableLiveData<List<Achievement>>()

    var snackBarContent = MutableLiveData("")

    suspend fun getAchievementList(){
        achievementList.postValue(achievementRepository.getAchievements())
    }

    suspend fun advanceAchievement(id: Int, value: Float){
        val advanced = achievementList.value!![id].advanceAchievement(value)
        if(advanced){
            snackBarContent.postValue("\uD83C\uDF89 Otrzymujesz osiągnięcie: " + achievementList.value!![id].name + " \uD83C\uDF89")
        }
        achievementRepository.updateAchievement(id, achievementList.value!![id])
        getAchievementList()
    }

    fun resetSnackbar() {
        snackBarContent.postValue("")
    }
}