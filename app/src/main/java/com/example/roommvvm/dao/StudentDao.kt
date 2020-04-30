package com.example.roommvvm.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roommvvm.entity.Student

//Data Access Object : interface dimana semua query ditempatkan
@Dao

//membuat interface StudentDao untu mengakses db
interface StudentDao {

//    query untuk menampilkan seluruh data dari tabel student
    @Query("Select * from student")
    fun getAll(): List<Student>

//    query untuk insert data ke tabel student
    @Insert
    fun insertStudent(item: Student)
}