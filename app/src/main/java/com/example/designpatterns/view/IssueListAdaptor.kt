package com.example.designpatterns.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.designpatterns.databinding.ListItemBinding
import com.example.designpatterns.model.GithubIssue

class IssueListAdaptor(val list : ArrayList<GithubIssue>) : RecyclerView.Adapter<IssueListAdaptor.IssueViewHolder>() {

    class IssueViewHolder(binding : ListItemBinding) : RecyclerView.ViewHolder(binding.root){
        var itemBinding : ListItemBinding = binding

        companion object{
            fun from(parent : ViewGroup) : IssueViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val itemBinding = ListItemBinding.inflate(layoutInflater, parent, false)
                return IssueViewHolder(itemBinding)
            }
        }

        fun bind(model : GithubIssue){
          itemBinding.model = model
          itemBinding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): IssueViewHolder {
        return IssueViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: IssueViewHolder , position: Int) {
        val model = list[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}