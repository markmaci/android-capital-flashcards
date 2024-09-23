package com.example.flashcards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.flashcards.ui.theme.FlashcardsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlashcardsTheme() {
                QuizApp()
            }
        }
    }
}

data class Question(
    val questionText: String,
    val answer: String
)

@Composable
fun QuizApp() {
    val questions = remember {
        listOf(
            Question("What is the capital of France?", "Paris"),
            Question("What is the capital of Germany?", "Berlin"),
            Question("What is the capital of Italy?", "Rome"),
            Question("What is the capital of Spain?", "Madrid"),
            Question("What is the capital of Japan?", "Tokyo"),
            Question("What is the capital of Albania?", "Tirana"),
            Question("What is the capital of Kosovo?", "Pristina"),
            Question("What is the capital of Czechia?", "Prague"),
            Question("What is the capital of Greece?", "Athens"),
            Question("What is the capital of Morocco?", "Rabat"),
            Question("What is the capital of Switzerland?", "Bern"),
            Question("What is the capital of Austria?", "Vienna"),
            Question("What is the capital of Australia?", "Canberra"),
            Question("What is the capital of Canada?", "Ottawa"),
            Question("What is the capital of Brazil?", "Brasília"),
            Question("What is the capital of Argentina?", "Buenos Aires"),
            Question("What is the capital of South Africa?", "Pretoria"),
            Question("What is the capital of Egypt?", "Cairo"),
            Question("What is the capital of Nigeria?", "Abuja"),
            Question("What is the capital of Kenya?", "Nairobi"),
            Question("What is the capital of China?", "Beijing"),
            Question("What is the capital of India?", "New Delhi"),
            Question("What is the capital of Russia?", "Moscow"),
            Question("What is the capital of Mexico?", "Mexico City"),
            Question("What is the capital of Saudi Arabia?", "Riyadh"),
            Question("What is the capital of Turkey?", "Ankara"),
            Question("What is the capital of Thailand?", "Bangkok"),
            Question("What is the capital of Indonesia?", "Jakarta"),
            Question("What is the capital of Malaysia?", "Kuala Lumpur"),
            Question("What is the capital of Philippines?", "Manila"),
            Question("What is the capital of Vietnam?", "Hanoi"),
            Question("What is the capital of Singapore?", "Singapore"),
            Question("What is the capital of New Zealand?", "Wellington"),
            Question("What is the capital of South Korea?", "Seoul"),
            Question("What is the capital of North Korea?", "Pyongyang"),
            Question("What is the capital of United Kingdom?", "London"),
            Question("What is the capital of Ireland?", "Dublin"),
            Question("What is the capital of Norway?", "Oslo"),
            Question("What is the capital of Sweden?", "Stockholm"),
            Question("What is the capital of Finland?", "Helsinki"),
            Question("What is the capital of Denmark?", "Copenhagen"),
            Question("What is the capital of Poland?", "Warsaw"),
            Question("What is the capital of Hungary?", "Budapest"),
            Question("What is the capital of Ukraine?", "Kyiv"),
            Question("What is the capital of Romania?", "Bucharest"),
            Question("What is the capital of Bulgaria?", "Sofia"),
            Question("What is the capital of Netherlands?", "Amsterdam"),
            Question("What is the capital of Belgium?", "Brussels"),
            Question("What is the capital of Portugal?", "Lisbon"),
            Question("What is the capital of Chile?", "Santiago"),
            Question("What is the capital of Colombia?", "Bogotá"),
            Question("What is the capital of Peru?", "Lima"),
            Question("What is the capital of Venezuela?", "Caracas"),
            Question("What is the capital of Iran?", "Tehran"),
            Question("What is the capital of Iraq?", "Baghdad"),
            Question("What is the capital of Israel?", "Jerusalem"),
            Question("What is the capital of Jordan?", "Amman"),
            Question("What is the capital of Lebanon?", "Beirut"),
            Question("What is the capital of Pakistan?", "Islamabad"),
            Question("What is the capital of Bangladesh?", "Dhaka"),
            Question("What is the capital of Sri Lanka?", "Sri Jayawardenepura Kotte"),
            Question("What is the capital of Nepal?", "Kathmandu"),
            Question("What is the capital of Afghanistan?", "Kabul"),
            Question("What is the capital of Myanmar?", "Naypyidaw"),
            Question("What is the capital of Ethiopia?", "Addis Ababa"),
            Question("What is the capital of Tanzania?", "Dodoma"),
            Question("What is the capital of Uganda?", "Kampala"),
            Question("What is the capital of Zimbabwe?", "Harare"),
            Question("What is the capital of Algeria?", "Algiers"),
            Question("What is the capital of Sudan?", "Khartoum"),
            Question("What is the capital of Libya?", "Tripoli"),
            Question("What is the capital of Tunisia?", "Tunis"),
            Question("What is the capital of Ecuador?", "Quito"),
            Question("What is the capital of Bolivia?", "Sucre"),
            Question("What is the capital of Paraguay?", "Asunción"),
            Question("What is the capital of Uruguay?", "Montevideo"),
            Question("What is the capital of Cuba?", "Havana"),
            Question("What is the capital of Jamaica?", "Kingston"),
            Question("What is the capital of Haiti?", "Port-au-Prince"),
            Question("What is the capital of Dominican Republic?", "Santo Domingo"),
            Question("What is the capital of United States?", "Washington, D.C."),
            Question("What is the capital of Iceland?", "Reykjavik"),
            Question("What is the capital of Slovenia?", "Ljubljana"),
            Question("What is the capital of Croatia?", "Zagreb"),
            Question("What is the capital of Slovakia?", "Bratislava"),
            Question("What is the capital of Lithuania?", "Vilnius"),
            Question("What is the capital of Latvia?", "Riga"),
            Question("What is the capital of Estonia?", "Tallinn"),
            Question("What is the capital of Mongolia?", "Ulaanbaatar"),
            Question("What is the capital of Maldives?", "Malé"),
            Question("What is the capital of Qatar?", "Doha"),
            Question("What is the capital of United Arab Emirates?", "Abu Dhabi"),
            Question("What is the capital of Oman?", "Muscat"),
            Question("What is the capital of Yemen?", "Sana'a"),
            Question("What is the capital of Kazakhstan?", "Nur-Sultan"),
            Question("What is the capital of Kyrgyzstan?", "Bishkek"),
            Question("What is the capital of Uzbekistan?", "Tashkent"),
            Question("What is the capital of Turkmenistan?", "Ashgabat"),
            Question("What is the capital of Azerbaijan?", "Baku")
        ).shuffled()
    }


    var currentQuestionIndex by remember { mutableStateOf(0) }
    var userInput by remember { mutableStateOf("") }
    var quizComplete by remember { mutableStateOf(false) }
    var correctAnswers by remember { mutableStateOf(0) }
    var totalQuestions by remember { mutableStateOf(0) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    fun submitAnswer() {
        val currentQuestion = questions[currentQuestionIndex]
        val correctAnswer = currentQuestion.answer.trim().lowercase()
        val userAnswer = userInput.trim().lowercase()
        val isCorrect = userAnswer == correctAnswer

        totalQuestions++

        if (isCorrect) {
            correctAnswers++
        }

        scope.launch {
            snackbarHostState.showSnackbar(
                message = if (isCorrect) "Correct!" else "Incorrect. The correct answer is ${currentQuestion.answer}.",
                duration = SnackbarDuration.Short
            )
        }

        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            userInput = ""
        } else {
            quizComplete = true
            userInput = ""
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = "Congratulations! You've completed the quiz.",
                    duration = SnackbarDuration.Long
                )
            }
        }
    }


    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            if (quizComplete) {
                Text(
                    text = "Quiz Complete!",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Your final score: $correctAnswers/$totalQuestions",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(
                    onClick = {
                        currentQuestionIndex = 0
                        userInput = ""
                        quizComplete = false
                        correctAnswers = 0
                        totalQuestions = 0
                    }
                ) {
                    Text("Restart Quiz")
                }
            } else {
                Text(
                    text = "Score: $correctAnswers/$totalQuestions",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                val currentQuestion = questions[currentQuestionIndex]

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = currentQuestion.questionText,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                OutlinedTextField(
                    value = userInput,
                    onValueChange = { userInput = it },
                    label = { Text("Your Answer") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            submitAnswer()
                        }
                    ),
                    singleLine = true
                )

                Button(
                    onClick = {
                        submitAnswer()
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text("Submit Answer")
                }
            }
        }
    }
}