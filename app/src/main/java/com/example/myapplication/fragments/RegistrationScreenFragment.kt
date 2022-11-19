package com.example.myapplication.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRegistrationScreenBinding
import com.example.myapplication.network.DataBaseHelper


class RegistrationScreenFragment : Fragment(R.layout.fragment_registration_screen) {
    private lateinit var handler:DataBaseHelper
    private lateinit var binding: FragmentRegistrationScreenBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentRegistrationScreenBinding.bind(view)
        handler= DataBaseHelper(requireContext())
        binding.gender.setOnClickListener{
            val dialog=Dialog(requireContext())
            dialog.setContentView(R.layout.gender_dialog)
            dialog.window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.setCancelable(false)
            dialog.show()
            val male=dialog.findViewById<TextView>(R.id.MaleTv)
            val female=dialog.findViewById<TextView>(R.id.FemaleTv)
            val other=dialog.findViewById<TextView>(R.id.otherTv)

            male.setOnClickListener {
                binding.gender.setText(male.text)
                dialog.dismiss()
            }
            female.setOnClickListener {
                binding.gender.setText(female.text)
                dialog.dismiss()
            }
            other.setOnClickListener {
                binding.gender.setText(other.text)
                dialog.dismiss()
            }

        }
        binding.saveData.setOnClickListener {
            var name =binding.editTextTextPersonName.text
            var mobileNo=binding.mobileET.text
            var pass=binding.passEt.text
            var gender=binding.gender.text
            handler.insertUserData(name.toString(),mobileNo.toString(),pass.toString(),gender.toString())
            findNavController().navigate(R.id.action_registrationScreenFragment_to_mainScreenFragment)
            Toast.makeText(requireContext(),"user create Successfully",Toast.LENGTH_SHORT).show()

        }
    }
}