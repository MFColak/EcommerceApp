package com.android.mfcolak.ecommerceapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.mfcolak.ecommerceapp.R
import com.android.mfcolak.ecommerceapp.model.User
import com.android.mfcolak.ecommerceapp.databinding.FragmentRegisterBinding
import com.android.mfcolak.ecommerceapp.utils.RegisterValidation
import com.android.mfcolak.ecommerceapp.utils.Resource
import com.android.mfcolak.ecommerceapp.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDoYouHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.apply {
            buttonRegister.setOnClickListener {
                val user = User(
                    firstname.text.toString().trim(),
                    lastname.text.toString().trim(),
                    emailEt.text.toString().trim(),
                )
                val password = passwordEt.text.toString()
                viewModel.createAccountWithEmailAndPassword(user, password)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.register.collect{
                when(it){
                    is Resource.Loading ->{

                    }
                    is Resource.Success -> {
                        Log.d("test", it.data.toString())
                    }
                    is Resource.Error ->{
                        Log.e("RegisterFragment", it.message.toString())
                    }
                    else-> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.validation.collect{validadion ->
                if (validadion.email is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.emailEt.apply {
                            requestFocus()
                            error = validadion.email.message
                        }
                    }
                }

                if (validadion.password is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.passwordEt.apply {
                            requestFocus()
                            error = validadion.password.message
                        }
                    }
                }
            }
        }
    }

}