package com.faithdeveloper.resume.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faithdeveloper.resume.*
import com.faithdeveloper.resume.Utils.CHILD
import com.faithdeveloper.resume.Utils.PARENT
import com.faithdeveloper.resume.Utils.PROJECTS
import com.faithdeveloper.resume.Utils.SKILLS
import com.faithdeveloper.resume.databinding.ExperienceItemBinding
import com.faithdeveloper.resume.databinding.HeadersItemBinding
import com.faithdeveloper.resume.databinding.ProjectItemBinding
import com.faithdeveloper.resume.databinding.SkillsItemBinding
import com.faithdeveloper.resume.models.Child
import com.faithdeveloper.resume.models.Experience
import com.faithdeveloper.resume.models.Headers
import com.faithdeveloper.resume.models.Project

class HeadersAdapter(private val headers: MutableList<Headers>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    inner class HeadersViewHolder(val binding: HeadersItemBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(item: Headers) {
            binding.title.text = item.title
            super.bind(item)
        }
    }

    inner class SkillsViewHolder(private val binding: SkillsItemBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(item: Headers) {
            binding.skill.text = (item.child.data.first()) as String
            super.bind(item)
        }
    }

    inner class ExperienceViewHolder(private val binding: ExperienceItemBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(item: Headers) {
            val experience = item.child.data.first() as Experience
            binding.companyName.text = experience.company
            binding.title.text = experience.title
            binding.whatIDid.text = experience.whatIDid
            super.bind(item)
        }
    }

    inner class ProjectsViewHolder(private val binding: ProjectItemBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(item: Headers) {
            val project = item.child.data.first() as Project
            binding.title.text = project.title
            binding.projectLink.text = project.link
            binding.description.text = project.description
            super.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            R.layout.headers_item -> {
                HeadersViewHolder(
                    HeadersItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            R.layout.skills_item -> SkillsViewHolder(
                SkillsItemBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            R.layout.experience_item -> ExperienceViewHolder(
                ExperienceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> ProjectsViewHolder(
                ProjectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if(headers[position].type == PARENT){
            holder as HeadersViewHolder
            holder.apply {
                binding.title.setOnClickListener {
                    expandOrCollapseHeader(headers[position], position)
                }
                binding.dropdownMenu.setOnClickListener {
                    expandOrCollapseHeader(headers[position], position)
                }
            }
        }
        holder.bind(headers[position])
    }


    override fun getItemCount() = headers.size

    override fun getItemViewType(position: Int): Int {
        val layout = if (headers[position].type == PARENT) {
            R.layout.headers_item
        } else {
            when (headers[position].child.type) {
                SKILLS -> {
                    R.layout.skills_item
                }
                PROJECTS -> {
                    R.layout.project_item
                }
                else -> R.layout.experience_item
            }
        }
        return layout
    }

    private fun expandOrCollapseHeader(item: Headers, adapterPosition: Int) {
        if (item.isExpanded) {
            collapseHeaderRow(item, adapterPosition)
        } else {
            expandParentRowPosition(item, adapterPosition)
        }
    }

    private fun expandParentRowPosition(item: Headers, adapterPosition: Int) {
        item.isExpanded = true
        var nextPosition = adapterPosition
        if (item.type == PARENT) {
            item.child.data.forEach { subData ->
                val header = Headers()
                header.type = CHILD
                val subChild = Child()
                subChild.type = item.child.type
                subChild.data.add(subData)
                header.child = subChild
                headers.add(++nextPosition, header)
            }
            notifyDataSetChanged()
        }
    }


    private fun collapseHeaderRow(item: Headers, adapterPosition: Int) {
        val currentChildren = item.child.data
        headers[adapterPosition].isExpanded = false
        var count = 0
        if (headers[adapterPosition].type == PARENT) {
            currentChildren.forEach { _ ->
                headers.removeAt(adapterPosition + 1)
                count += 1
            }
            notifyDataSetChanged()
        }
//        notifyItemRangeRemoved(adapterPosition + 1, adapterPosition + count)
    }

    override fun getItemId(position: Int) = position.toLong()


}