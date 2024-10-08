package com.example.quizapp

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_qs"
    const val CORRECT_ANSWERS : String = "correct answers"

    fun getQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val q1 = Question(
            1, "Which country's flag is this?",
            R.drawable.india_flag,
            "China",
            "Nepal",
            "Bhutan",
            "India",
            4
        )
        questionsList.add(q1)

        val q2 = Question(
            2, "Which country's flag is this?",
            R.drawable.china_flag,
            "China",
            "Nepal",
            "SriLanka",
            "Myanmar",
            1
        )
        questionsList.add(q2)

        val q3 = Question(
            3, "Which country's flag is this?",
            R.drawable.japan_flag,
            "Indonesia",
            "Thailand",
            "Japan",
            "Singapore",
            3
        )
        questionsList.add(q3)

        val q4 = Question(
            4, "Which country's flag is this?",
            R.drawable.aus_flag,
            "New Zealnd",
            "Australia",
            "England",
            "USA",
            2
        )
        questionsList.add(q4)

        val q5 = Question(
            5, "Which country's flag is this?",
            R.drawable.germany_flag,
            "Argentina",
            "Portugal",
            "Belarus",
            "Germany",
            4
        )
        questionsList.add(q5)

        val q6 = Question(
            6, "Which country's flag is this?",
            R.drawable.israel_flag,
            "Egypt",
            "Nigeria",
            "South Africa",
            "Israel",
            4
        )
        questionsList.add(q6)

        val q7 = Question(
            7, "Which country's flag is this?",
            R.drawable.iran_flag,
            "Iran",
            "Iraq",
            "Kuwait",
            "Sudan",
            1
        )
        questionsList.add(q7)

        val q8 = Question(
            8, "Which country's flag is this?",
            R.drawable.russia_flag,
            "Ukraine",
            "Russia",
            "Afghanistan",
            "Japan",
            2
        )
        questionsList.add(q8)

        val q9 = Question(
            9, "Which country's flag is this?",
            R.drawable.belgium_flag,
            "Austria",
            "France",
            "Scotland",
            "Belgium",
            4
        )
        questionsList.add(q9)

        val q10 = Question(
            10, "Which country's flag is this?",
            R.drawable.netherland_flag,
            "England",
            "Netherland",
            "Belgium",
            "Spain",
            2
        )
        questionsList.add(q10)
        return questionsList
    }
}