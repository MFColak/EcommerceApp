package com.android.mfcolak.ecommerceapp.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.android.mfcolak.ecommerceapp.R
import com.android.mfcolak.ecommerceapp.activities.ShoppingActivity
import com.google.firebase.auth.FirebaseAuth

class SplashFragment : Fragment() {


    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            if (firebaseAuth.currentUser != null) {
                Intent(requireActivity(), ShoppingActivity::class.java).also { intent ->
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)//undo = exit
                    startActivity(intent)
                }
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_registerFragment)
            }
        }, 2000)

    }

}