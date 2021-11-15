package com.example.designpatterns.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.designpatterns.R
import com.example.designpatterns.model.GithubIssue

class IssueListAdaptor(val list : ArrayList<GithubIssue>) : RecyclerView.Adapter<IssueListAdaptor.IssueViewHolder>() {

    class IssueViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.title)
        val date = itemView.findViewById<TextView>(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): IssueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return IssueViewHolder(layoutInflater.inflate(R.layout.list_item , parent, false))
    }

    override fun onBindViewHolder(holder: IssueViewHolder , position: Int) {
        val model = list[position]

        holder.title.text = model.title
        holder.date.text = model.date
    }

    override fun getItemCount(): Int {
        return list.size
    }

}