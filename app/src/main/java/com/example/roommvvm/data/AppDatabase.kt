package com.example.roommvvm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roommvvm.dao.StudentDao
import com.example.roommvvm.entity.Student

// class database ini digunakan untuk melakukan pengecekan pernyataan sql pada waktu compile
//yaitu dengan mendeklarasikan room database yang digunakan di dalam aplikasi
@Database(entities = arrayOf(Student::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

//    memasukkan data import StudentDao ke fungsi
    abstract fun studentDao(): StudentDao

//    companion object digunakan untuk menyimpan semua object
    companion object {
        private var INSTANCE: AppDatabase? = null

//        fun getInstance digunakan untuk mengecek apakah database sudah ada atau belum
//        jika belum maka akan dibuild dahulu
        fun getInstance(context: Context): AppDatabase? {

//            kondisi jika database belum ada
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {

//                  maka build database dijalankan
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "student-database")
                        .build()
                }
            }
            return INSTANCE
        }

//      fungsi untuk menghapus database
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}