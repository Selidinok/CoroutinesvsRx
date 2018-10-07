package com.example.android.coroutinesvsrx.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.domain.entities.Repository
import com.example.android.coroutinesvsrx.databinding.RepositoryItemBinding

/**
 * Created by Artem Rumyantsev on 21:16 06.10.2018.

 */
class RepositoriesAdapter : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    private var repositories = mutableListOf<Repository>()

    fun setData(data: List<Repository>) {
        repositories.clear()
        repositories.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) = ViewHolder(
        RepositoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    class ViewHolder(
        val binding: RepositoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Repository) {
            binding.repository = item
            item.ownerId.toString()
            binding.executePendingBindings()
        }
    }
}