package com.example.assistant.viewModel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assistant.data.Exercise
import com.example.assistant.repositories.ExerciseRepository
import com.example.assistant.repositories.LocalExerciseRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExerciseViewModel(
    private val exerciseRepository: ExerciseRepository = LocalExerciseRepository()
) : ViewModel() {

    val exercisePlan = MutableLiveData<List<Exercise>>()
    var currentExercise = MutableLiveData<Exercise>()

    val currentTime: MutableLiveData<Float> = MutableLiveData(null)
    private var _finishedExercise = MutableStateFlow(false)
    var finishedExercise = _finishedExercise.asStateFlow()

    private lateinit var timer: CountDownTimer

    suspend fun getExercisePlan(){
        exercisePlan.postValue(exerciseRepository.getExercises())
        currentTime.postValue(null)
    }

    suspend fun startupTimer(id: Int) {
        delay(1000)

        timer = object: CountDownTimer((currentExercise.value!!.durationSeconds * 1000).toLong(), 20) {
            override fun onTick(millisUntilFinished: Long) { currentTime.postValue(millisUntilFinished.toFloat()/1000.0f)}

            override fun onFinish(){
                viewModelScope.launch { completeExercise(id) }
                _finishedExercise.value = (true)
            }
        }
        timer.start()
    }

    suspend fun getExercise(id: Int): Exercise{
        _finishedExercise.value = (false)
        val exercise = exerciseRepository.getExercise(id)
        currentExercise.postValue(exercise)

        currentTime.postValue(currentExercise.value?.durationSeconds?.toFloat())

        return exercise
    }

    suspend fun completeExercise(id: Int){
        exerciseRepository.completeExercise(id)
    }

    fun resetComplete(){
        _finishedExercise.value = false
    }
}