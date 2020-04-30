package com.example.roommvvm.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// class entity digunakan untuk mendeklarasikan tabel beserta isinya (struktur/kolom) dalam database
@Entity
data class Student (
//    deklarasi id sebagai primary key dan id tersebut autoincrement
    @PrimaryKey(autoGenerate = true) var id: Int? = null,

//    deklarasi tipe data pada field name, kolom ini inputan dari user
    @ColumnInfo var name: String = ""
)