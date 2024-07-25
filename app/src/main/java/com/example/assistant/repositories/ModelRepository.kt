package com.example.assistant.repositories

import com.example.assistant.BuildConfig
import com.example.assistant.data.Message
import com.example.assistant.data.Sender
import com.example.assistant.json.GeminiResponseSerializable
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json

interface ModelRepository {
    suspend fun generateResponse(message: Message): Message
    suspend fun generatePhotoDescription(): Message
}

class GeminiModelRepository : ModelRepository {

    private val client = HttpClient(CIO){
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(BuildConfig.GEMINI_API_KEY, null)
                }
            }
        }
        install(ContentNegotiation){
            json()
        }
    }

    override suspend fun generateResponse(message: Message): Message {
        val response = client.post(BuildConfig.GEMINI_URL){
            headers {
               append(HttpHeaders.ContentType, "application/json")
            }
            setBody(
               "{\n" +
                       "    \"contents\": [\n" +
                       "        {\n" +
                       "            \"role\": \"user\",\n" +
                       "            \"parts\": [\n" +
                       "                {\n" +
                       "                    \"text\": \"" + message.content + "\"\n" +
                       "                },\n" +
                       "            ]\n" +
                       "        }\n" +
                       "    ],\n" +
                       "    \"systemInstruction\": {\n" +
                       "      \"parts\": [\n" +
                       "        {\n" +
                       "            \"text\": \"Jesteś wykwalifikowaną osobą pomagającą dziecku z otyłością.\\n\\nOdpisuj krótkimi wiadomościami, maksymalnie 1 paragraf\"\n" +
                       "        },\n" +
                       "      ]\n" +
                       "    },\n" +
                       "    \"generationConfig\": {\n" +
                       "        \"maxOutputTokens\": 8192,\n" +
                       "        \"temperature\": 1,\n" +
                       "        \"topP\": 0.95,\n" +
                       "    },\n" +
                       "    \"safetySettings\": [\n" +
                       "        {\n" +
                       "            \"category\": \"HARM_CATEGORY_HATE_SPEECH\",\n" +
                       "            \"threshold\": \"BLOCK_MEDIUM_AND_ABOVE\"\n" +
                       "        },\n" +
                       "        {\n" +
                       "            \"category\": \"HARM_CATEGORY_DANGEROUS_CONTENT\",\n" +
                       "            \"threshold\": \"BLOCK_MEDIUM_AND_ABOVE\"\n" +
                       "        },\n" +
                       "        {\n" +
                       "            \"category\": \"HARM_CATEGORY_SEXUALLY_EXPLICIT\",\n" +
                       "            \"threshold\": \"BLOCK_MEDIUM_AND_ABOVE\"\n" +
                       "        },\n" +
                       "        {\n" +
                       "            \"category\": \"HARM_CATEGORY_HARASSMENT\",\n" +
                       "            \"threshold\": \"BLOCK_MEDIUM_AND_ABOVE\"\n" +
                       "        }\n" +
                       "    ],\n" +
                       "}"
            )
        }

        try {
            return if (response.status == HttpStatusCode.OK) {
                val geminiResponse: List<GeminiResponseSerializable> = response.body()

                var stringResponse = ""

                for (r in geminiResponse)
                    stringResponse += r.candidates[0].content.parts[0].text

                Message(stringResponse.dropLast(1), Sender.MODEL)
            } else {
                Message("Błąd Gemini: " + response.status.toString(), Sender.MODEL)
            }
        }
        catch (e: ConnectTimeoutException){
            return Message("Błąd Gemini: " + "Timeout", Sender.MODEL)
        }
    }

    override suspend fun generatePhotoDescription(): Message {
        val response = client.post(BuildConfig.GEMINI_URL){
            headers {
                append(HttpHeaders.ContentType, "application/json")
            }
            setBody(
                "{\n" +
                        "  \"contents\": {\n" +
                        "    \"role\": \"user\",\n" +
                        "    \"parts\": [\n" +
                        "      {\n" +
                        "      \"fileData\": {\n" +
                        "        \"mimeType\": \"image/jpeg\",\n" +
                        "        \"fileUri\": \"gs://generativeai-downloads/images/scones.jpg\"\n" +
                        "        }\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"text\": \"Napisz krótko co jest na tym zdjęciu, wymień tylko jedzenie\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}"
            )
        }

        try {
            return if (response.status == HttpStatusCode.OK) {
                val geminiResponse: List<GeminiResponseSerializable> = response.body()

                var stringResponse = ""

                for (r in geminiResponse)
                    stringResponse += r.candidates[0].content.parts[0].text

                Message(stringResponse.dropLast(1) + "Zapisuje to!", Sender.MODEL)
            } else {
                Message("Błąd Gemini: " + response.status.toString(), Sender.MODEL)
            }
        }
        catch (e: ConnectTimeoutException){
            return Message("Błąd Gemini: " + "Timeout", Sender.MODEL)
        }
    }
}