package com.example.roommvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roommvvm.fragment.NameListFragment
import com.example.roommvvm.fragment.NewNameFragment
import timber.log.Timber

class MainActivity : AppCompatActivity(),

//    memanggil 2 fragment yang ada di package fragment
    NewNameFragment.OnFragmentInteractionListener,
    NameListFragment.OnFragmentInteractionListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        kondisi jika savedInstanceState = null
        if (savedInstanceState == null) {

//            maka memanggil fungsi goToStudentListFragment
            goToStudentListFragment()
        }

        Timber.plant(Timber.DebugTree())
    }

//    memanggil fungsi untuk menampilkan fragment name list
    override fun goToStudentListFragment() {
        val manager = supportFragmentManager

        val transaction = manager.beginTransaction()

//       membuat perpindahan fragment
        transaction.replace(R.id.flContent, NameListFragment.newInstance())

//    transaksi dimulai
        transaction.commit()
    }

    override fun goToNewNameFragment() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

//      membuat perpindahan fragment
        transaction.replace(R.id.flContent, NewNameFragment.newInstance())

//       transaksi dimulai
        transaction.commit()
    }
}
