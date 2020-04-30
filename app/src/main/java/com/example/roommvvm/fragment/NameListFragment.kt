package com.example.roommvvm.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.roommvvm.R
import com.example.roommvvm.data.AppDatabase
import com.example.roommvvm.helper.StudentRecyclerAdapter
import com.example.roommvvm.viewmodel.NewStudentViewModel
import kotlinx.android.synthetic.main.fragment_name_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */

// class NameListFragment  digunakan untuk memanggil tampilan fragment (list nama)
class NameListFragment : Fragment() {

//  membuat objek untuk listener
    private var listener: OnFragmentInteractionListener? = null

//    deklarasi ViewModel
    private lateinit var mViewModel: NewStudentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        memanggil view model melalui variabel
        mViewModel = ViewModelProviders.of(this).get(NewStudentViewModel::class.java)
        mViewModel.retrieveStudent().observe(this, Observer {
            Timber.i("menerima perubahan data ${it.size}")

//            menyimpan nilai Live data ke StudentRecyclerAdapter
            rvList.adapter = StudentRecyclerAdapter(it)
        })
    }

//    fungsi onCreateView pemanggil view pertamakali ketika fragment menampilkan layoutnya
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name_list, container, false)
    }

//   fungsi ini dipanggil sesaat setelah onCreateView()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        menentukan jenis layout yang digunakan, yaitu linear
        rvList.layoutManager = LinearLayoutManager(activity)

//      fungsi onclick pada button tambah data baru
        btnAdd.setOnClickListener {

//          deklarasi instance dari class student dao
            val dao =  AppDatabase.getInstance(this.context!!)?.studentDao()

//          launch query yg ada dalam class student dao
            GlobalScope.launch {
                dao?.getAll()
            }

            listener?.goToNewNameFragment()
        }
    }

//   fungsi yang berjalan ketika fragment diaktifkan
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

//    fungsi yg berjalan ketika fragment dinonaktifkan
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun goToNewNameFragment()
    }

    companion object {
        @JvmStatic
        fun newInstance() = NameListFragment()
    }

}
