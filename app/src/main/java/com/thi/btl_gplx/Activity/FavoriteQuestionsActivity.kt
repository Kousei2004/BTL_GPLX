package com.thi.btl_gplx.Activity;

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.example.btl_gplx.Database.DatabaseManager
import com.thi.btl_gplx.Adapter.FavoriteQuestionsAdapter
import com.thi.btl_gplx.R

class FavoriteQuestionsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var favoriteQuestionsAdapter: FavoriteQuestionsAdapter
    private lateinit var dbHelper: DatabaseManager
    private lateinit var searchInput: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_questions)

        // Các phần tử khác
        val button = findViewById<Button>(R.id.button2)
        button.setOnClickListener {
            // Thực hiện hành động khi Button được nhấn
            onBackPressed() // Quay lại Activity trước đó
        }

        recyclerView = findViewById(R.id.favoriteRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Khởi tạo DatabaseManager và lấy danh sách câu hỏi yêu thích
        dbHelper = DatabaseManager(this)
        val savedQuestions = dbHelper.getSavedQuestions()

        // Khởi tạo và thiết lập adapter cho RecyclerView
        favoriteQuestionsAdapter = FavoriteQuestionsAdapter(savedQuestions.toMutableList(), savedQuestions.toMutableList(), this)

        recyclerView.adapter = favoriteQuestionsAdapter

        // Khởi tạo AutoCompleteTextView và thiết lập bộ gợi ý
        searchInput = findViewById(R.id.searchInput)

        // Tạo ArrayAdapter cho AutoCompleteTextView
        val questionList = savedQuestions.map { it.question } // Chỉ lấy danh sách câu hỏi
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, questionList)
        searchInput.setAdapter(adapter)

        // Lắng nghe sự kiện thay đổi trong AutoCompleteTextView (khi gõ)
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
                // Không cần xử lý gì ở đây
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                // Không cần xử lý gì ở đây
            }

            override fun afterTextChanged(text: Editable?) {
                // Khi văn bản thay đổi, lấy chuỗi tìm kiếm
                val query = text.toString().trim()
                if (query.isEmpty()) {
                    // Nếu chuỗi tìm kiếm trống, không cần lọc, hiển thị tất cả câu hỏi
                    favoriteQuestionsAdapter.filter.filter("") // Hiển thị tất cả câu hỏi
                } else {
                    // Lọc danh sách câu hỏi theo chuỗi tìm kiếm
                    favoriteQuestionsAdapter.filter.filter(query)
                }
            }
        })

        // Lắng nghe sự kiện khi người dùng chọn một câu hỏi từ gợi ý
        searchInput.setOnItemClickListener { _, _, position, _ ->
            val selectedQuestion = adapter.getItem(position).toString()
            // Khi người dùng chọn câu hỏi từ gợi ý, lọc theo câu hỏi đã chọn
            favoriteQuestionsAdapter.filter.filter(selectedQuestion)
        }
    }
}
