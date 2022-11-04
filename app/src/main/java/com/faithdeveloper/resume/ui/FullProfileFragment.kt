package com.faithdeveloper.resume.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.faithdeveloper.resume.*
import com.faithdeveloper.resume.Utils.EXPERIENCE
import com.faithdeveloper.resume.Utils.PARENT
import com.faithdeveloper.resume.Utils.PROJECTS
import com.faithdeveloper.resume.Utils.SKILLS
import com.faithdeveloper.resume.Utils.call
import com.faithdeveloper.resume.Utils.launchLink
import com.faithdeveloper.resume.Utils.launchWhatsapp
import com.faithdeveloper.resume.Utils.sendEmail
import com.faithdeveloper.resume.databinding.FullProfileBinding
import com.faithdeveloper.resume.models.Child
import com.faithdeveloper.resume.models.Experience
import com.faithdeveloper.resume.models.Headers
import com.faithdeveloper.resume.models.Project

class FullProfileFragment : Fragment() {
    private var _binding: FullProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HeadersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        setUpAdapter()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FullProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        clickListeners()
        setUpRecycler()
        super.onViewCreated(view, savedInstanceState)
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

    private fun clickListeners() {
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
            launchLink(Utils.TWITTER)
        }
        binding.contacts.github.setOnClickListener {
            launchLink(Utils.GITHUB)
        }
        binding.contacts.linkedIn.setOnClickListener {
            launchLink(Utils.LINKEDIN)

        }
    }

    private fun setUpAdapter() {
        val headers = resources.getStringArray(R.array.headers)
        val listOfHeaders = mutableListOf<Headers>()

        val projectTitles = resources.getStringArray(R.array.project_titles)
        val projectLinks = resources.getStringArray(R.array.links)
        val projectDescription =  resources.getStringArray(R.array.descriptions)
        val listOfProjects = mutableListOf<Any>()

        val listOfCompanies = resources.getStringArray(R.array.companies)
        val listOfTitle = resources.getStringArray(R.array.company_titles)
        val listOfWhatIDid = resources.getStringArray(R.array.whatIDid)
        val listOfExperiences = mutableListOf<Any>()

       projectTitles.onEachIndexed { index, projects ->
           listOfProjects.add(Project(projectTitles[index], projectLinks[index], projectDescription[index]))
       }
        listOfWhatIDid.onEachIndexed { index, whatIDid ->
            listOfExperiences.add(Experience(listOfCompanies[index], listOfTitle[index], listOfWhatIDid[index]))
        }
        val mapOfChildren = mapOf(
            SKILLS to Child(SKILLS, resources.getStringArray(R.array.skills).toMutableList()),
            PROJECTS to Child(PROJECTS, listOfProjects),
            EXPERIENCE to Child(EXPERIENCE, listOfExperiences)
        )
        headers.onEach {
            listOfHeaders.add(
                Headers(it, PARENT, mapOfChildren[it]!!)
            )
        }
        adapter = HeadersAdapter(listOfHeaders)
    }

    private fun setUpRecycler() {
        binding.recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycler.adapter = adapter
    }
}