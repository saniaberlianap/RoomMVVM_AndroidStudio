package com.example.roommvvm.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roommvvm.R
import com.example.roommvvm.entity.Student

// class StudentRecyclerAdapter digunakan untuk menampung data dan menampilkannya sebagai list view
class StudentRecyclerAdapter(private val myDataset: List<Student>):
        RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewHolder>() {

//    mendeklarasikan TextView beserta id nya
    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tvName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)

        return StudentViewHolder(v)
    }

//    fun getItemCount untuk menghitung jumlah data yang diterima oleh adapter
    override fun getItemCount(): Int {
        return myDataset.size
    }

//    fun ini untuk mengatur cara penampilan data
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
//        penampilan data di textView tvName yang ada di recycler_item.xml
        holder.tvName.text = myDataset[position].name
    }
}