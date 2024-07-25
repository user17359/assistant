package com.example.assistant.data.presets

import com.example.assistant.data.InterventionElement
import com.example.assistant.data.Message
import com.example.assistant.data.Sender
import com.example.assistant.data.interventionElements.GiveDecisions
import com.example.assistant.data.interventionElements.InterventionOption
import com.example.assistant.data.interventionElements.PhotoToModel
import com.example.assistant.data.interventionElements.SendMessage
import com.example.assistant.data.interventionElements.Stop


val testIntervention: InterventionElement = SendMessage(
    Message("Czy mógłbyś mi wysłać zdjęcie swojego śniadania?", Sender.MODEL),
    GiveDecisions(
        listOf(
            InterventionOption(
                "\uD83D\uDCF7",
                PhotoToModel(
                    Stop()
                )
            ),
            InterventionOption(
                "Nie teraz \uD83D\uDE14",
                SendMessage(
                    Message("Nie teraz \uD83D\uDE14", Sender.FAKEUSER),
                    Stop()
                )
            )
        )
    )
)

