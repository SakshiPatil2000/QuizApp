package com.example.quizappkotlin

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizappkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val questions=arrayOf("What is built in database in Android Studio ?","" +
            "What is an full form of APK in Android Studio ?",
        "In which year, first android was released by Google ?")

    private val options= arrayOf(arrayOf("MySQL","SQLite","Firebase"), arrayOf("Application Programming Interface", "Android Programming Interface",
        "Android Package Information"), arrayOf("2010","2006","2008"))

    private val correctAnswers= arrayOf(1,0,2)

    private var currentQuestionIndex=0
    private var score=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()

        binding.option1btn.setOnClickListener {
            checkAnswer(0)
        }

        binding.option2btn.setOnClickListener {
            checkAnswer(1)
        }
        binding.option3btn.setOnClickListener {
            checkAnswer(2)
        }

        binding.restartbtn.setOnClickListener {
            restartQuiz()
        }

    }

    private fun correctButtonColors(buttonIndex:Int){
        when(buttonIndex){
            0->binding.option1btn.setBackgroundColor(Color.GREEN)
            1->binding.option2btn.setBackgroundColor(Color.GREEN)
            2->binding.option3btn.setBackgroundColor(Color.GREEN)
        }
    }
    private fun wrongButtonColors(buttonIndex:Int){
        when(buttonIndex){
            0 ->binding.option1btn.setBackgroundColor(Color.RED)
            1 ->binding.option2btn.setBackgroundColor(Color.RED)
            2 ->binding.option3btn.setBackgroundColor(Color.RED)
        }
    }
    private fun resetButtonColors(){
        binding.option1btn.setBackgroundColor(Color.rgb(50,59,96))
        binding.option2btn.setBackgroundColor(Color.rgb(50,59,96))
        binding.option3btn.setBackgroundColor(Color.rgb(50,59,96))
    }
    private fun showResults(){
        Toast.makeText(this,"Your score : $score out of ${questions.size}",Toast.LENGTH_LONG).show()
        binding.restartbtn.isEnabled=true
    }
    private fun displayQuestion(){
        binding.questionText.text=questions[currentQuestionIndex]
        binding.option1btn.text=options[currentQuestionIndex][0]
        binding.option2btn.text=options[currentQuestionIndex][1]
        binding.option3btn.text=options[currentQuestionIndex][2]
        resetButtonColors()
    }

    private fun checkAnswer(selectedAnswerIndex:Int){

        val correctAnswerIndex =correctAnswers[currentQuestionIndex]

        if(selectedAnswerIndex==correctAnswerIndex){
            score++
            correctButtonColors(selectedAnswerIndex)

        }else{
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if(currentQuestionIndex < questions.size -1){
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestion()},1000)

        }
        else
        {
            showResults()
        }


    }
    private fun restartQuiz(){
        currentQuestionIndex=0
        score=0
        displayQuestion()
        binding.restartbtn.isEnabled=false
    }


}