package com.example.myapplication.fragments

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.NewsAdapter
import com.example.myapplication.databinding.FragmentMainScreenBinding
import com.example.myapplication.viewmodels.MainScreenViewModel
import kotlinx.android.synthetic.main.fragment_main_screen.*


class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {
    private lateinit var binding:FragmentMainScreenBinding
    private lateinit var viewModel: MainScreenViewModel
    private lateinit var newsLayoutManager: LinearLayoutManager
    private  var newsAdapter= NewsAdapter(emptyList())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentMainScreenBinding.bind(view)
        newsLayoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        viewModel=ViewModelProvider(this)[MainScreenViewModel::class.java]
        binding.newsRv.layoutManager=newsLayoutManager
        binding.newsRv.adapter=newsAdapter
        val dialog=Dialog(requireContext())
        dialog.setContentView(R.layout.loader)
        dialog.window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        dialog.show()
        viewModel.insertUsersDataStatus.observe(viewLifecycleOwner) {
            dialog.dismiss()
            newsAdapter.submitList(it)
        }




        binding.searchNews.setOnClickListener{
            var et=queryEt.text.trim().toString()
            Log.d("et",et.toString())
            if (et.isBlank()){
                Toast.makeText(requireContext(),"Invalid Search",Toast.LENGTH_SHORT).show()
            }else{
                viewModel.searchNews(et)
                dialog.show()
                queryEt.clearFocus()
            }
        }


    }
}