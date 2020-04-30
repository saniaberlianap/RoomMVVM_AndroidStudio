package com.example.roommvvm.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

import com.example.roommvvm.R
import com.example.roommvvm.viewmodel.NewStudentViewModel
import kotlinx.android.synthetic.main.fragment_new_name.*

/**
 * A simple [Fragment] subclass.
 */

// class NewNameFragment  digunakan untuk memanggil tampilan fragment (input nama)
class NewNameFragment : Fragment() {

//    membuat objek untuk listener
    private var listener: OnFragmentInteractionListener? = null

//    deklarasi ViewModel
    private lateinit var mViewModel: NewStudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        memanggil view model melalui variabel
        mViewModel = ViewModelProviders.of(this).get(NewStudentViewModel::class.java)
    }

//    fungsi onCreateView pemanggil view pertamakali ketika fragment menampilkan layoutnya
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_name, container, false)
    }

//    fungsi ini dipanggil sesaat setelah onCreateView()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        fungsi pada button tambah ketika di klik
        button.setOnClickListener {
            val input = editText.text.toString().trim()

//            validasi apabila field input  kosong
            if (input.isEmpty()) {
                Toast.makeText(activity, "Nama dibutuhkan", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            validasi apabila inputan lebih dari 30 huruf
            if (input.length > 30) {
                Toast.makeText(activity, "Nama terlalu panjang", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            set data input
            mViewModel.storeMovie(input)

//          menampilkan toast dan memanggil fungsi goToStudentListFragment yang digunakan intent ke fragment NameListFragment
            Toast.makeText(activity, "$input entered", Toast.LENGTH_SHORT).show()
            listener?.goToStudentListFragment()
        }
    }

//    fungsi yang berjalan ketika fragment diaktifkan
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
        fun goToStudentListFragment()
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewNameFragment()
    }
}
