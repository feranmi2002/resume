package com.faithdeveloper.resume

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.net.URLEncoder

object Utils {
    const val TWITTER = "twitter"
    const val GITHUB = "github"
    const val LINKEDIN = "linkedIn"
    const val PARENT = 0
    const val CHILD = 1
    const val SKILLS = "Skills"
    const val EXPERIENCE = "Experience"
    const val PROJECTS = "Projects"
    fun Fragment.launchWhatsapp() {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(
                "whatsapp://send?phone=${getString(R.string.phone_number)}&text=${
                    URLEncoder.encode(
                        getString(R.string.offer),
                        "UTF-8"
                    )
                }"
            )
        }
        if (intent.resolveActivity(this.requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(
                this.requireContext(),
                getString(R.string.whatsapp_unavailable),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun Fragment.sendEmail() {
        val recipientArray = arrayOf(getString(R.string.email_address))
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, recipientArray)
            putExtra(Intent.EXTRA_TEXT, getString(R.string.offer))
        }
        return if (intent.resolveActivity(this.requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(
                this.requireContext(),
                getString(R.string.email_unavailable),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun Fragment.call() {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse(getString(R.string.phone_number))
        }
        return if (intent.resolveActivity(this.requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(
                this.requireContext(),
               getString(R.string.phone_unavailable),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun Fragment.launchLink(type:String) {
        val link = when(type){
            TWITTER -> getString(R.string.twitter_url)
            GITHUB -> getString(R.string.github_url)
           else-> getString(R.string.linked_in_url)
        }
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(link)
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(
                this.requireContext(),
                getString(R.string.failed_to_open_link),
                Toast.LENGTH_LONG
            ).show()
        }
    }

}