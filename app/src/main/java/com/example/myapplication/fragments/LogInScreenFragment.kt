package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLogInScreenBinding
import com.example.myapplication.network.DataBaseHelper


class LogInScreenFragment : Fragment(R.layout.fragment_log_in_screen) {
    private lateinit var binding: FragmentLogInScreenBinding

    private lateinit var handler :DataBaseHelper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler= DataBaseHelper(requireContext())
        binding= FragmentLogInScreenBinding.bind(view)
        binding.registerNow.setOnClickListener {
            findNavController().navigate(R.id.action_logInScreenFragment_to_registrationScreenFragment)
        }
        binding.loginTv.setOnClickListener{
            val mobile=binding.mobileET.text.toString()
            val pass=binding.passET2.text.toString()
            if (handler.userPresent(mobile,pass)){
                findNavController().navigate(R.id.action_logInScreenFragment_to_mainScreenFragment)
            }else{
                Toast.makeText(requireContext(),"incorrect Credentials",Toast.LENGTH_SHORT).show()
            }
        }
    }

}