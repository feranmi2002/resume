package com.faithdeveloper.resume.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.faithdeveloper.resume.R
import com.faithdeveloper.resume.Utils.GITHUB
import com.faithdeveloper.resume.Utils.LINKEDIN
import com.faithdeveloper.resume.Utils.TWITTER
import com.faithdeveloper.resume.Utils.call
import com.faithdeveloper.resume.Utils.launchLink
import com.faithdeveloper.resume.Utils.launchWhatsapp
import com.faithdeveloper.resume.Utils.sendEmail
import com.faithdeveloper.resume.databinding.IntroScreenBinding

class IntroFragment : Fragment() {
    private var _binding: IntroScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = IntroScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        clickListeners()
        loadProfilePicture()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun loadProfilePicture() {
        binding.profilePicture.setImageBitmap(BitmapFactory.decodeStream(resources.openRawResource(R.raw.profile_picture)))
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun clickListeners() {
        binding.hireMe.setOnClickListener {
            sendEmail()
        }
        binding.contacts.email.setOnClickListener {
            sendEmail()
        }
        binding.contacts.phone.setOnClickListener {
            call()
        }
        binding.contacts.whatsapp.setOnClickListener {
            launchWhatsapp()
        }
        binding.contacts.twitter.setOnClickListener {
            launchLink(TWITTER)
        }
        binding.contacts.github.setOnClickListener {
            launchLink(GITHUB)
        }
        binding.contacts.linkedIn.setOnClickListener {
            launchLink(LINKEDIN)

        }
        binding.knowMe.setOnClickListener {
            findNavController().navigate(IntroFragmentDirections.actionIntroFragmentToFullProfileFragment())
        }
    }
}