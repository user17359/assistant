package com.example.assistant.data

enum class Tier {
    None,
    Bronze,
    Silver,
    Gold,
    Diamond
}

private fun GetNextTier(previous: Tier): Tier{
    return when (previous) {
        Tier.None -> {
            Tier.Bronze
        }
        Tier.Bronze -> {
            Tier.Silver
        }
        Tier.Silver -> {
            Tier.Gold
        }
        Tier.Gold -> {
            Tier.Diamond
        }
        Tier.Diamond -> {
            Tier.Diamond
        }
    }
}

class Achievement (
    val name: String,
    val description: String,
    var currentNumber: Float = 0f,
    var tier: Tier = Tier.None,
    private val requirements: Map<Tier, Int>,
    var requiredNumber: Int = requirements[GetNextTier(tier)]!!
){
    fun advanceAchievement(value: Float) : Boolean{
        var advanced = false
        currentNumber += value
        while(currentNumber >= requiredNumber) {
            tier = GetNextTier(tier)
            requiredNumber = requirements[GetNextTier(tier)]!!
            advanced = true
        }
        return advanced
    }
}