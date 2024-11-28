package com.thi.btl_gplx.Adapter;


import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.appcompat.app.AlertDialog


import com.example.btl_gplx.Database.DatabaseManager
import com.thi.btl_gplx.Models.QuestionModel
import com.thi.btl_gplx.R
import com.thi.btl_gplx.databinding.ListItemFavoriteQuestionBinding

class FavoriteQuestionsAdapter(
    private var favoriteQuestions: MutableList<QuestionModel>, // Danh sách câu hỏi hiện tại
    private val originalQuestions:  MutableList<QuestionModel>, // Danh sách câu hỏi gốc
    private val context: Context
) : RecyclerView.Adapter<FavoriteQuestionsAdapter.FavoriteViewHolder>(), Filterable, Parcelable {


    constructor(parcel: Parcel) : this(
        TODO("favoriteQuestions"),
        TODO("originalQuestions"),
        TODO("context")
    ) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ListItemFavoriteQuestionBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val question = favoriteQuestions[position]
        holder.bind(question)

        // Xử lý sự kiện nhấn vào item
        holder.itemView.setOnClickListener {
            showDeleteDialog(question, position)
        }
    }

    override fun getItemCount(): Int = favoriteQuestions.size

    // Phương thức lọc
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList: MutableList<QuestionModel> = ArrayList()

                if (constraint.isNullOrEmpty()) {
                    // Nếu không có từ khóa, hiển thị toàn bộ danh sách
                    filteredList.addAll(originalQuestions)
                } else {
                    // Tìm kiếm câu hỏi
                    val filterPattern = constraint.toString().toLowerCase().trim()
                    for (item in originalQuestions) {
                        if (item.question.toLowerCase().contains(filterPattern)) {
                            filteredList.add(item)
                        }
                    }
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                favoriteQuestions = results?.values as MutableList<QuestionModel>
                notifyDataSetChanged() // Cập nhật RecyclerView
            }
        }
    }


    private fun showDeleteDialog(question: QuestionModel, position: Int) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_delete_question, null)

        val dialogBuilder = AlertDialog.Builder(context)
            .setView(dialogView)

        val dialog = dialogBuilder.create()

        // Thiết lập nền trong suốt cho dialog
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // Lấy các thành phần trong dialog
        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialogTitle)
        val dialogMessage = dialogView.findViewById<TextView>(R.id.dialogMessage)
        val btnDelete = dialogView.findViewById<Button>(R.id.btnDelete)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)

        dialogTitle.text = "Xác nhận xóa"
        dialogMessage.text = "Bạn có chắc chắn muốn xóa câu hỏi: \n ${question.question}?"

        btnDelete.setOnClickListener {
            // Create an instance of DatabaseManager
            val dbHelper = DatabaseManager(context)

            // Call removeQuestion() on the DatabaseManager instance
            dbHelper.removeQuestion(question)

            // Remove from both favoriteQuestions and originalQuestions
            favoriteQuestions.removeAt(position)
            originalQuestions.removeAt(position) // Xóa câu hỏi khỏi originalQuestions

            // Notify the adapter that the data has been changed
            notifyItemRemoved(position)

            // Call the filter again to reload all data
            getFilter().filter("") // Gọi lại filter để làm mới danh sách câu hỏi

            // Dismiss the dialog
            dialog.dismiss()
        }



        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }




    inner class FavoriteViewHolder(private val binding: ListItemFavoriteQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(question: QuestionModel) {
            val questionText = question.question.trim().replace(Regex("^Câu hỏi \\d+:\\s*"), "Câu hỏi: ")
            binding.questionText.text = questionText

            val optionsText = question.options.joinToString(separator = "\n") { "- $it" }
            binding.optionsText.text = optionsText

            binding.correctAnswerText.text = "Đáp án: ${question.correct}"

            if (!question.imageUrl.isNullOrEmpty()) {
                val imageName = question.imageUrl.split(".")[0]
                val resourceId = context.resources.getIdentifier(imageName, "drawable", context.packageName)
                if (resourceId != 0) {
                    binding.questionImage.setImageResource(resourceId)
                    binding.questionImage.visibility = View.VISIBLE
                } else {
                    binding.questionImage.visibility = View.GONE
                }
            } else {
                binding.questionImage.visibility = View.GONE
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FavoriteQuestionsAdapter> {
        override fun createFromParcel(parcel: Parcel): FavoriteQuestionsAdapter {
            return FavoriteQuestionsAdapter(parcel)
        }

        override fun newArray(size: Int): Array<FavoriteQuestionsAdapter?> {
            return arrayOfNulls(size)
        }
    }
}
