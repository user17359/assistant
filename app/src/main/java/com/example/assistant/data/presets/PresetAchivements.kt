package com.example.assistant.data.presets

import com.example.assistant.data.Achievement
import com.example.assistant.data.Tier

val setOfAchievements: Set<Achievement> = setOf(
    Achievement(
        name = "Olimpijczyk",
        description = "Ćwicz przez %d dni",
        requirements = mapOf(
            Tier.Bronze to 7,
            Tier.Silver to 30,
            Tier.Gold to 183,
            Tier.Diamond to 365
        )
    ),
    Achievement(
        name = "Czasowy zawodnik",
        description = "Wykonuj ćwiczenia przez %d minut/y",
        requirements = mapOf(
            Tier.Bronze to 1,
            Tier.Silver to 30,
            Tier.Gold to 240,
            Tier.Diamond to 720
        )
    ),
    Achievement(
        name = "Owocowy znawca",
        description = "Wykonuj quiz o warzywach i owocach %d razy",
        tier = Tier.Silver,
        currentNumber = 23f,
        requirements = mapOf(
            Tier.Bronze to 1,
            Tier.Silver to 10,
            Tier.Gold to 50,
            Tier.Diamond to 100
        )
    ),
    Achievement(
        name = "Odżywczy entuzjasta",
        description = "Wykonuj quiz o składnikach odżywczych %d razy",
        tier = Tier.Gold,
        currentNumber = 69f,
        requirements = mapOf(
            Tier.Bronze to 1,
            Tier.Silver to 10,
            Tier.Gold to 50,
            Tier.Diamond to 100
        )
    ),
    Achievement(
        name = "Żywieniowy specjalista",
        description = "Wykonuj quiz o piramidzie żywieniowej %d razy",
        tier = Tier.Diamond,
        currentNumber = 100f,
        requirements = mapOf(
            Tier.Bronze to 1,
            Tier.Silver to 10,
            Tier.Gold to 50,
            Tier.Diamond to 100
        )
    ),
    Achievement(
        name = "Kuchenny reporter",
        description = "Wstaw zdjęcie posiłku do chatbota %d razy",
        tier = Tier.Bronze,
        currentNumber = 5f,
        requirements = mapOf(
            Tier.Bronze to 1,
            Tier.Silver to 10,
            Tier.Gold to 50,
            Tier.Diamond to 100
        )
    ),
    Achievement(
        name = "Świeżak",
        description = "Zmień awatar",
        requirements = mapOf(
            Tier.Bronze to 0,
            Tier.Silver to 0,
            Tier.Gold to 0,
            Tier.Diamond to 1
        )
    ),
)