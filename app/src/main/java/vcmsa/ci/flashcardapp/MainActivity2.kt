package vcmsa.ci.flashcardapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class MainActivity2 : AppCompatActivity() {
    private var Questions: TextView? = null
    private var Answers: TextView? = null
    private var editTextText: EditText? = null

    private var questions = arrayOf(
        " Nelson Mandela was South Africa’s first Black president",
        "The apartheid system in South Africa officially ended in 1990",
        "The discovery of diamonds and gold in South Africa had no significant impact on the country’s development",
        "The Anglo-Zulu War took place in the 19th century",
        "The Sharpeville Massacre occurred in 1960 during a protest against pass laws"
    )

    private var answers = arrayOf(
        true,
        false,
        false,
        true,
        true
    )

    private var currentQuestionIndex = 0
    private var score = 0
    private var hasAnswered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        Questions = findViewById(R.id.Questions)
        Answers = findViewById(R.id.Answers)
        editTextText = findViewById(R.id.editTextText)

        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnTrue = findViewById<Button>(R.id.btnTrue)
        val btnFalse = findViewById<Button>(R.id.btnFalse)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val btnExit = findViewById<Button>(R.id.btnExit)
        val btnScore = findViewById<Button>(R.id.btnScore)

        disableAnswerButtons()
        btnNext.isEnabled = false
        btnScore.isEnabled = false

        enableAnswerButtons()
        btnNext.isEnabled = true
        btnScore.isEnabled = true

        btnStart.setOnClickListener {
            Start()
        }

        btnFalse.setOnClickListener {
            checkAnswer(false)
        }

        btnTrue.setOnClickListener{
            checkAnswer(true)
        }

        btnNext.setOnClickListener {
            nextQuestion()
        }

        btnExit.setOnClickListener {
            finishAffinity()
            exitProcess(1)
        }

        btnScore.setOnClickListener {
            val intent = Intent(this,MainActivity3::class.java)
            intent.putExtra("score",score)
            startActivity(intent)
        }

    }

    private fun disableAnswerButtons() {
        findViewById<Button>(R.id.btnTrue).isEnabled = false
        findViewById<Button>(R.id.btnFalse).isEnabled = false
    }

    private fun enableAnswerButtons() {
       findViewById<Button>(R.id.btnTrue).isEnabled = true
        findViewById<Button>(R.id.btnFalse).isEnabled = true
    }

    private fun Start() {
        currentQuestionIndex = 0
        score = 0
        hasAnswered = false
        Answers?.text = ""
        editTextText?.setText("")
        Questions?.text = questions[currentQuestionIndex]
        enableAnswerButtons()
    }

    private fun checkAnswer(userAnswer: Boolean) {
        if (!hasAnswered) {
            if (userAnswer == answers[currentQuestionIndex]) {
                Answers?.text = "Correct!"
                score++
            } else {
                Answers?.text = "Incorrect!"
            }
            hasAnswered = true
            disableAnswerButtons()
        }
    }
    private fun nextQuestion() {
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            Questions?.text = questions[currentQuestionIndex]
            Answers?.text = ""
            hasAnswered = false
            enableAnswerButtons()
        } else {
            showScore()
        }
    }

    private fun showScore() {
        Questions?.text = "Quiz Completed!"
        Answers?.text = ""
        val feedback = when {
            score == 5 -> "Excellent work!"
            score >= 3 -> "Good job!"
            else -> "Keep practicing!"
        }
        editTextText?.setText("Your score: $score/5\n$feedback")
    }
}