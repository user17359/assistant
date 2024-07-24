package com.example.assistant.repositories

import com.example.assistant.data.Exercise
import com.example.assistant.data.setOfExercises

interface ExerciseRepository {
    suspend fun getExercises(): List<Exercise>
    suspend fun getExercise(id: Int): Exercise
    suspend fun completeExercise(id: Int)
}

class LocalExerciseRepository() : ExerciseRepository {

    private val exercises: MutableList<Exercise> = setOfExercises.toMutableList()


    override suspend fun getExercises(): List<Exercise> {
        return exercises
    }

    override suspend fun getExercise(id:Int): Exercise {
        return exercises.first(){it.id == id}
    }

    override suspend fun completeExercise(id: Int) {
        exercises.first{it.id == id}.status = true
    }

}