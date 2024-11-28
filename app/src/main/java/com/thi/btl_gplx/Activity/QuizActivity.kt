package com.thi.btl_gplx.Activity;

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import com.example.btl_gplx.Database.DatabaseManager
import com.thi.btl_gplx.Models.QuestionModel
import com.thi.btl_gplx.R
import com.thi.btl_gplx.databinding.ActivityQuizBinding
import com.thi.btl_gplx.databinding.ScoreDialogBinding


class QuizActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        var questionModelList: List<QuestionModel> = listOf()
        var time: String = ""
    }

    lateinit var binding: ActivityQuizBinding

    var score = 0
    var selectedAnswer = ""
    var currentQuestionIndex = 0

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Xử lý sự kiện click của các nút
        binding.apply {
            btn0.setOnClickListener(this@QuizActivity)
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            btn3.setOnClickListener(this@QuizActivity)
            nextBtn.setOnClickListener(this@QuizActivity)
            prevBtn.setOnClickListener(this@QuizActivity)

            // Kiểm tra trạng thái yêu thích khi activity được tạo
            val currentQuestion = questionModelList[currentQuestionIndex]
            updateFavoriteButtonState(currentQuestion)
            // Xử lý sự kiện cho nút starButton
            starButton.setOnClickListener {
                val currentQuestion = questionModelList[currentQuestionIndex]

                if (isQuestionFavorite(currentQuestion)) {
                    // Nếu câu hỏi đã được yêu thích, xóa khỏi yêu thích
                    removeFavoriteQuestion(currentQuestion)
                    starButton.setBackgroundResource(R.drawable.ic_star) // Set empty star
                    starText.text = "Thêm vào yêu thích" // Update text
                } else {
                    // Nếu câu hỏi chưa được yêu thích, lưu vào yêu thích
                    saveQuestionToDatabase(currentQuestion)
                    starButton.setBackgroundResource(R.drawable.ic_star_filled) // Set filled star
                    starText.text = "Đã thêm vào yêu thích" // Update text
                }
            }
        }

        loadQuestions()
        startTimer()
    }

    private fun updateFavoriteButtonState(questionModel: QuestionModel) {
        val dbHelper = DatabaseManager(this)
        if (isQuestionFavorite(questionModel)) {
            binding.starButton.setBackgroundResource(R.drawable.ic_star_filled) // Set filled star
            binding.starText.text = "Đã thêm vào yêu thích" // Update text
        } else {
            binding.starButton.setBackgroundResource(R.drawable.ic_star) // Set empty star
            binding.starText.text = "Thêm vào yêu thích" // Update text
        }
    }

    fun isQuestionFavorite(question: QuestionModel): Boolean {
        val dbHelper = DatabaseManager(this)
        val savedQuestions = dbHelper.getSavedQuestions() // Lấy tất cả câu hỏi đã được lưu
        return savedQuestions.any { it.question == question.question } // Kiểm tra câu hỏi hiện tại có trong danh sách yêu thích không
    }

    private fun saveQuestionToDatabase(questionModel: QuestionModel) {
        val dbHelper = DatabaseManager(this)
        dbHelper.saveQuestion(questionModel)
    }

    private fun removeFavoriteQuestion(questionModel: QuestionModel) {
        val dbHelper = DatabaseManager(this)
        dbHelper.removeQuestion(questionModel)
    }

    private fun startTimer() {
        val totalTimeInMillis = time.toInt() * 60 * 1000L
        object : CountDownTimer(totalTimeInMillis, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60
                val remainingSeconds = seconds % 60
                binding.timerIndicatorTextview.text = String.format("%02d:%02d", minutes, remainingSeconds)
            }

            override fun onFinish() {
                finishQuiz()
            }
        }.start()
    }

    private fun loadQuestions() {
        selectedAnswer = ""

        // Kiểm tra nếu đã hết câu hỏi
        if (currentQuestionIndex == questionModelList.size || currentQuestionIndex < 0) {
            finishQuiz() // Gọi finishQuiz nếu đã hết câu hỏi hoặc chỉ mục không hợp lệ
            return
        }

        binding.apply {
            val currentQuestion = questionModelList[currentQuestionIndex]
            questionIndicatorTextview.text = "Câu hỏi ${currentQuestionIndex + 1}/${questionModelList.size}"
            questionProgressIndicator.progress =
                (currentQuestionIndex.toFloat() / questionModelList.size.toFloat() * 100).toInt()
            questionTextview.text = currentQuestion.question

            // Cập nhật các đáp án
            val options = currentQuestion.options
            btn0.text = if (options.size > 0) options[0] else ""
            btn1.text = if (options.size > 1) options[1] else ""
            btn2.text = if (options.size > 2) options[2] else ""
            btn3.text = if (options.size > 3) options[3] else ""

            // Ẩn các nút đáp án không có
            btn0.visibility = if (options.size > 0) View.VISIBLE else View.GONE
            btn1.visibility = if (options.size > 1) View.VISIBLE else View.GONE
            btn2.visibility = if (options.size > 2) View.VISIBLE else View.GONE
            btn3.visibility = if (options.size > 3) View.VISIBLE else View.GONE

            // Kiểm tra nếu câu hỏi có ảnh
            val imageUrl = currentQuestion.imageUrl
            if (!imageUrl.isNullOrEmpty()) {
                questionImage.visibility = View.VISIBLE
                val resourceId = resources.getIdentifier(imageUrl.split(".")[0], "drawable", packageName)
                if (resourceId != 0) {
                    questionImage.setImageResource(resourceId)
                } else {
                    questionImage.visibility = View.GONE
                }
            } else {
                questionImage.visibility = View.GONE
            }

            // Cập nhật trạng thái yêu thích
            updateFavoriteButtonState(currentQuestion)

            prevBtn.visibility = if (currentQuestionIndex > 0) View.VISIBLE else View.GONE
        }
    }


    override fun onClick(view: View) {
        binding.apply {
            btn0.setBackgroundColor(getColor(R.color.gray))
            btn1.setBackgroundColor(getColor(R.color.gray))
            btn2.setBackgroundColor(getColor(R.color.gray))
            btn3.setBackgroundColor(getColor(R.color.gray))
        }

        val clickedBtn = view as Button

        if (clickedBtn.id == R.id.next_btn) {
            // Kiểm tra đáp án
            if (selectedAnswer == questionModelList[currentQuestionIndex].correct) {
                score++
            }

            // Chuyển sang câu hỏi tiếp theo nếu không vượt quá phạm vi
            if (currentQuestionIndex + 1 < questionModelList.size) {
                currentQuestionIndex++ // Tăng chỉ số câu hỏi
                loadQuestions()
            } else {
                // Nếu đã hết câu hỏi, kết thúc bài thi
                finishQuiz()
            }
        } else if (clickedBtn.id == R.id.prevBtn) { // Xử lý nút quay lại
            // Quay lại câu hỏi trước nếu không vượt quá phạm vi
            if (currentQuestionIndex - 1 >= 0) {
                currentQuestionIndex-- // Giảm chỉ số câu hỏi
                loadQuestions()
            }
        } else {
            selectedAnswer = clickedBtn.text.toString()
            clickedBtn.setBackgroundColor(getColor(R.color.blue))
        }


        // Cập nhật lại trạng thái starButton cho câu hỏi hiện tại
        val currentQuestion = questionModelList[currentQuestionIndex]
        updateFavoriteButtonState(currentQuestion)
    }


    private fun finishQuiz() {
        val totalQuestions = questionModelList.size
        val percentage = ((score.toFloat() / totalQuestions.toFloat()) * 100).toInt()

        // Log để kiểm tra giá trị tính toán
        Log.d("QuizActivity", "Total Questions: $totalQuestions, Score: $score, Percentage: $percentage")

        // Tạo binding cho Score Dialog
        val dialogBinding = ScoreDialogBinding.inflate(layoutInflater)

        dialogBinding.apply {
            scoreProgressIndicator.progress = percentage
            scoreProgressText.text = "$percentage %"

            if (percentage > 80) {
                scoreTitle.text = "Chúc mừng! Bạn đã qua"
                scoreTitle.setTextColor(Color.BLUE)
            } else {
                scoreTitle.text = "Rất tiếc! Bạn chưa qua"
                scoreTitle.setTextColor(Color.RED)
            }

            scoreSubtitle.text = "$score trên $totalQuestions câu hỏi đã trả lời đúng"

            finishBtn.setOnClickListener {
                // Log khi người dùng nhấn nút finish
                Log.d("QuizActivity", "Finish button clicked")
                finish() // Đóng Activity và kết thúc bài thi
            }
        }

        // Log để kiểm tra việc tạo dialog
        Log.d("QuizActivity", "Showing Score Dialog")

        // Hiển thị dialog
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .show()

        // Đảm bảo cửa sổ dialog không có background mặc định
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // Log khi dialog được hiển thị
        Log.d("QuizActivity", "Dialog shown")
    }

}

