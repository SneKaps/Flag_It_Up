package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var progressBar:ProgressBar? = null
    private var tvprogressBar:TextView? = null
    private var ivImage:ImageView? = null
    private var tvQuestion:TextView? = null

    private var mUserName:String? = null
    private var mCorrectAns:Int = 0

    private var tvOption1:TextView? = null
    private var tvOption2:TextView? = null
    private var tvOption3:TextView? = null
    private var tvOption4:TextView? = null
    private var btnSubmit: Button? = null

    private var mCurrentPosition : Int = 1
    private var mQuestionsList : ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        tvprogressBar = findViewById(R.id.tv_progress)
        ivImage = findViewById(R.id.iv_flag)
        tvQuestion = findViewById(R.id.tv_question)
        tvOption1 = findViewById(R.id.tv_option1)
        tvOption2 = findViewById(R.id.tv_option2)
        tvOption3 = findViewById(R.id.tv_option3)
        tvOption4 = findViewById(R.id.tv_option4)
        btnSubmit = findViewById(R.id.btn_submit)

        tvOption1?.setOnClickListener(this)
        tvOption2?.setOnClickListener(this)
        tvOption3?.setOnClickListener(this)
        tvOption4?.setOnClickListener(this)



        mQuestionsList = Constants.getQuestions()

        setQuestion()
        defaultOptionsView()
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        defaultOptionsView()
        val question: Question = mQuestionsList!![mCurrentPosition - 1]
        ivImage?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition
        tvprogressBar?.text = "$mCurrentPosition/${progressBar?.max}"
        tvQuestion?.text = question.qs
        tvOption1?.text = question.optionOne
        tvOption2?.text = question.optionTwo
        tvOption3?.text = question.optionThree
        tvOption4?.text = question.optionFour

        if(mCurrentPosition == mQuestionsList!!.size){
            btnSubmit?.text ="FINISH"
        }
        else{
            btnSubmit?.text = "SUBMIT"
        }
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        tvOption1?.let {
            options.add(0, it)
        }
        tvOption2?.let {
            options.add(1, it)
        }
        tvOption3?.let{
            options.add(2, it)
        }
        tvOption4?.let {
            options.add(3, it)
        }

        for(option in options){
            option.setTextColor(Color.parseColor("#808080"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(tv:TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#000000"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_option1->{
                tvOption1?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.tv_option2->{
                tvOption2?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.tv_option3->{
                tvOption3?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.tv_option4->{
                tvOption4?.let {
                    selectedOptionView(it, 4)
                }
            }
            R.id.btn_submit->{
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else->{
                            Toast.makeText(this, "You made it to the end!!!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, finish_page::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAns)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                            startActivity(intent)
                        }
                    }
                }
                else{
                    val question = mQuestionsList?.get(mCurrentPosition -1)
                    if(question!!.correctAns != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else{
                        mCorrectAns++
                    }
                    answerView(question.correctAns, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionsList!!.size){
                        btnSubmit?.text = "FINISH"
                    }
                    else{
                        btnSubmit?.text = "NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }
    private fun answerView(answer:Int, drawableView: Int){
        when(answer){
            1->{
                tvOption1?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2->{
                tvOption2?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3->{
                tvOption3?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4->{
                tvOption4?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}
