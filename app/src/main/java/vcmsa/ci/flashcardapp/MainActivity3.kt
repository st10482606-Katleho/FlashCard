package vcmsa.ci.flashcardapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.color.utilities.Score
import kotlin.system.exitProcess

class MainActivity3 : AppCompatActivity() {
    private var Score: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        Score = findViewById(R.id.btnScore)

        val btnRetry = findViewById<Button>(R.id.btnRetry)
        val btnExit = findViewById<Button>(R.id.btnExit)
        val btnCorrections = findViewById<Button>(R.id.btnCorrections)

        btnExit.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }

        btnRetry.setOnClickListener {
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
            finish()
        }

        btnCorrections.setOnClickListener {
            val intent = Intent(this,MainActivity4::class.java)
            startActivity(intent)
        }

        val score = intent.getIntExtra("score", 0)

        Score?.text = "Your Score: $score / 5"


            }
        }

