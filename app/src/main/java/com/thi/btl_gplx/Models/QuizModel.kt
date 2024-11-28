package com.thi.btl_gplx.Models


data class QuizModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val time: String,
    val questionList: List<QuestionModel>
) {
    // Hàm khởi tạo không có đối số
    constructor() : this("", "", "", "", emptyList())
}

data class QuestionModel(
    val question: String,
    val options: List<String>,
    val correct: String,
    val imageUrl: String? = null // Đường dẫn ảnh, có thể null
) {
    constructor() : this("", emptyList(), "", null)

}