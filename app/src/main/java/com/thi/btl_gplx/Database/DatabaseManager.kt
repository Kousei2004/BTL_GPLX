package com.example.btl_gplx.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.widget.Toast
import com.thi.btl_gplx.Models.QuestionModel
import com.thi.btl_gplx.Models.QuizModel

class DatabaseManager(private val context: Context) {

    private val DATABASE_NAME = "questions.db"
    private val DATABASE_VERSION = 1

    // Tên bảng cho câu hỏi và quiz
    private val TABLE_QUESTIONS = "saved_questions"
    private val TABLE_QUIZZES = "saved_quizzes"

    // Cột cho bảng câu hỏi
    private val COLUMN_QUESTION_ID = "id"
    private val COLUMN_QUESTION_TEXT = "question"
    private val COLUMN_OPTIONS = "options"
    private val COLUMN_CORRECT = "correct"
    private val COLUMN_IMAGE_URL = "imageUrl"

    // Cột cho bảng quiz
    private val COLUMN_QUIZ_ID = "id"
    private val COLUMN_QUIZ_TITLE = "title"
    private val COLUMN_QUIZ_SUBTITLE = "subtitle"
    private val COLUMN_QUIZ_TIME = "time"
    private val COLUMN_QUIZ_QUESTIONS = "questions"  // Lưu ID câu hỏi trong quiz

    // Hàm mở cơ sở dữ liệu
    private fun openDatabase(): SQLiteDatabase {
        return context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null)
    }

    // Tạo bảng câu hỏi
    private fun createQuestionsTable(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE IF NOT EXISTS $TABLE_QUESTIONS (
                $COLUMN_QUESTION_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_QUESTION_TEXT TEXT,
                $COLUMN_OPTIONS TEXT,
                $COLUMN_CORRECT TEXT,
                $COLUMN_IMAGE_URL TEXT
            )
        """
        db.execSQL(createTableQuery)
    }

    // Tạo bảng quiz
    private fun createQuizzesTable(db: SQLiteDatabase) {
        val createTableQuery = """
        CREATE TABLE IF NOT EXISTS $TABLE_QUIZZES (
            $COLUMN_QUIZ_ID TEXT PRIMARY KEY,
            $COLUMN_QUIZ_TITLE TEXT,
            $COLUMN_QUIZ_SUBTITLE TEXT,
            $COLUMN_QUIZ_TIME TEXT,
            $COLUMN_QUIZ_QUESTIONS TEXT  -- Lưu danh sách câu hỏi dưới dạng JSON
        )
    """
        db.execSQL(createTableQuery)
    }

    // Lưu câu hỏi vào cơ sở dữ liệu
    fun saveQuestion(question: QuestionModel) {
        val db = openDatabase()  // Mở cơ sở dữ liệu
        createQuestionsTable(db) // Tạo bảng nếu chưa có

        // Dùng ContentValues để chứa dữ liệu
        val values = ContentValues()

        // Lưu các trường vào ContentValues
        values.put(COLUMN_QUESTION_TEXT, question.question)  // Lưu câu hỏi
        values.put(COLUMN_OPTIONS, question.options.joinToString(","))  // Lưu các đáp án dưới dạng chuỗi
        values.put(COLUMN_CORRECT, question.correct)  // Lưu câu trả lời đúng
        values.put(COLUMN_IMAGE_URL, question.imageUrl)  // Lưu URL của hình ảnh nếu có

        // Chèn dữ liệu vào bảng
        db.insert(TABLE_QUESTIONS, null, values)

        db.close()  // Đóng cơ sở dữ liệu sau khi xong
    }


    fun saveQuiz(quiz: QuizModel) {
        val db = openDatabase()
        createQuizzesTable(db)

        val gson = com.google.gson.Gson() // Khởi tạo Gson
        val questionsJson = gson.toJson(quiz.questionList) // Chuyển danh sách câu hỏi thành JSON

        val values = ContentValues()
        values.put(COLUMN_QUIZ_ID, quiz.id)
        values.put(COLUMN_QUIZ_TITLE, quiz.title)
        values.put(COLUMN_QUIZ_SUBTITLE, quiz.subtitle)
        values.put(COLUMN_QUIZ_TIME, quiz.time)
        values.put(COLUMN_QUIZ_QUESTIONS, questionsJson) // Lưu JSON vào cột

        db.insert(TABLE_QUIZZES, null, values)
        db.close()
    }


    // Lấy danh sách câu hỏi đã lưu
    fun getSavedQuestions(): List<QuestionModel> {
        val db = openDatabase()
        createQuestionsTable(db)

        val cursor: Cursor = db.query(
            TABLE_QUESTIONS, null, null, null, null, null, null
        )
        val questions = mutableListOf<QuestionModel>()

        // Lấy chỉ mục cột để đảm bảo cột tồn tại
        val columnQuestionIndex = cursor.getColumnIndex(COLUMN_QUESTION_TEXT)
        val columnOptionsIndex = cursor.getColumnIndex(COLUMN_OPTIONS)
        val columnCorrectIndex = cursor.getColumnIndex(COLUMN_CORRECT)
        val columnImageUrlIndex = cursor.getColumnIndex(COLUMN_IMAGE_URL)


        // Kiểm tra nếu các chỉ mục cột hợp lệ (không phải -1)
        if (columnQuestionIndex == -1 || columnOptionsIndex == -1 || columnCorrectIndex == -1 || columnImageUrlIndex == -1) {
            // Nếu có cột không hợp lệ, trả về danh sách trống hoặc xử lý theo cách khác
            return questions
        }

        if (cursor.moveToFirst()) {
            do {
                val questionText = cursor.getString(columnQuestionIndex) ?: ""
                val optionsString = cursor.getString(columnOptionsIndex) ?: ""
                val correct = cursor.getString(columnCorrectIndex) ?: ""
                val imageUrl = cursor.getString(columnImageUrlIndex)

                // Log để kiểm tra dữ liệu
                Log.d("DatabaseManager", "Question: $questionText")
                Log.d("DatabaseManager", "Options: $optionsString")
                Log.d("DatabaseManager", "Correct: $correct")
                Log.d("DatabaseManager", "Image URL: $imageUrl")

                val options = if (optionsString.isNotEmpty()) optionsString.split(",") else emptyList()
                questions.add(QuestionModel(questionText, options, correct, imageUrl))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return questions
    }

    fun getSavedQuizzes(): List<QuizModel> {
        val db = openDatabase()
        createQuizzesTable(db)

        val cursor: Cursor = db.query(TABLE_QUIZZES, null, null, null, null, null, null)
        val quizzes = mutableListOf<QuizModel>()
        val gson = com.google.gson.Gson()

        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(COLUMN_QUIZ_ID)
            val titleIndex = cursor.getColumnIndex(COLUMN_QUIZ_TITLE)
            val subtitleIndex = cursor.getColumnIndex(COLUMN_QUIZ_SUBTITLE)
            val timeIndex = cursor.getColumnIndex(COLUMN_QUIZ_TIME)
            val questionsIndex = cursor.getColumnIndex(COLUMN_QUIZ_QUESTIONS)

            if (idIndex != -1 && titleIndex != -1 && subtitleIndex != -1 && timeIndex != -1 && questionsIndex != -1) {
                do {
                    val id = cursor.getString(idIndex) ?: ""
                    val title = cursor.getString(titleIndex) ?: ""
                    val subtitle = cursor.getString(subtitleIndex) ?: ""
                    val time = cursor.getString(timeIndex) ?: ""
                    val questionsJson = cursor.getString(questionsIndex) ?: ""

                    val questionList: List<QuestionModel> = gson.fromJson(
                        questionsJson,
                        Array<QuestionModel>::class.java
                    ).toList()

                    quizzes.add(QuizModel(id, title, subtitle, time, questionList))
                } while (cursor.moveToNext())
            }
        }

        cursor.close()
        db.close()
        return quizzes
    }



    // Xóa câu hỏi khỏi cơ sở dữ liệu
    fun removeQuestion(questionModel: QuestionModel) {
        val db = openDatabase()
        // Xác định điều kiện để xóa câu hỏi: tìm câu hỏi theo nội dung câu hỏi
        val selection = "$COLUMN_QUESTION_TEXT = ?"
        val selectionArgs = arrayOf(questionModel.question)  // Sử dụng nội dung câu hỏi

        // Xóa câu hỏi khỏi cơ sở dữ liệu
        val rowsDeleted = db.delete(TABLE_QUESTIONS, selection, selectionArgs)

        // Kiểm tra xem có câu hỏi nào bị xóa không
        if (rowsDeleted > 0) {
            // Hiển thị thông báo nếu câu hỏi đã được xóa thành công
            Toast.makeText(context, "Câu hỏi đã được xóa", Toast.LENGTH_SHORT).show()
        } else {
            // Thông báo nếu không tìm thấy câu hỏi để xóa
            Toast.makeText(context, "Không tìm thấy câu hỏi để xóa", Toast.LENGTH_SHORT).show()
        }

        db.close()
    }
}
