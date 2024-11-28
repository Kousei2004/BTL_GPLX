package com.thi.btl_gplx.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.thi.btl_gplx.Models.QuizModel
import com.google.firebase.database.FirebaseDatabase
import com.thi.btl_gplx.Adapter.QuizListAdapter
import com.thi.btl_gplx.databinding.ActivityDethidbBinding

class Activity_De_Thi_Db  : AppCompatActivity(){
    lateinit var binding: ActivityDethidbBinding
    lateinit var quizModelList: MutableList<QuizModel>
    lateinit var  adapter: QuizListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDethidbBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quizModelList = mutableListOf()

        // Ẩn RecyclerView và hiển thị ProgressBar khi bắt đầu tải dữ liệu
        binding.apply {
            progressBar.visibility = View.VISIBLE
            tvTitle.visibility = View.GONE
            recyclerView.visibility = View.GONE
        }

        getDataFromFirebase()

        binding.button2.setOnClickListener {
            goBackToHome()
        }
    }

    private fun setupRecyclerView() {
        adapter = QuizListAdapter(quizModelList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun getDataFromFirebase() {
        FirebaseDatabase.getInstance().reference
            .get()
            .addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    for (dataSnapshot1 in dataSnapshot.children) {
                        val quizModel = dataSnapshot1.getValue(QuizModel::class.java)
                        if (quizModel != null) {
                            val id = quizModel.id.toIntOrNull()
                            if (id != null && id in listOf(11, 12, 13)) {
                                quizModelList.add(quizModel)
                            }
                        }
                    }
                }

                // Hiển thị lại RecyclerView và TextView sau khi tải dữ liệu thành công
                binding.apply {
                    progressBar.visibility = View.GONE
                    tvTitle.visibility = View.VISIBLE
                    recyclerView.visibility = View.VISIBLE
                }

                setupRecyclerView()
            }
            .addOnFailureListener {
                // Xử lý lỗi: Ẩn ProgressBar, hiện TextView, và không hiển thị RecyclerView
                binding.apply {
                    progressBar.visibility = View.GONE
                    tvTitle.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Log.e("FirebaseError", "Failed to fetch data", it)
                Toast.makeText(this, "Lỗi khi tải dữ liệu", Toast.LENGTH_LONG).show()
            }
    }

    private fun goBackToHome() {
        onBackPressed()
        finish()
    }

}
