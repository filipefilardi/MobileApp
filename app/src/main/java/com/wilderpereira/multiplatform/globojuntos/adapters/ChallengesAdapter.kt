package com.wilderpereira.multiplatform.globojuntos.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.wilderpereira.multiplatform.globojuntos.models.Challenge
import com.wilderpereira.multiplatform.globojuntos.R

class ChallengesAdapter(val list: List<Challenge>, val clickListener: (Challenge) -> Unit) : RecyclerView.Adapter<ChallengeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ChallengeViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val challenge: Challenge = list[position]
        holder.bind(challenge, clickListener)
    }

    override fun getItemCount(): Int = list.size

}



class ChallengeViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.question_item, parent, false)) {

    private var questionContainter: LinearLayout? = null
    private var titleTv: TextView? = null
    private var descriptionTv: TextView? = null
    private var activeUsersCountTv: TextView? = null


    init {
        titleTv = itemView.findViewById(R.id.titleTv)
        descriptionTv = itemView.findViewById(R.id.descriptionTv)
        activeUsersCountTv = itemView.findViewById(R.id.activeUsersCountTv)
        questionContainter = itemView.findViewById(R.id.questionContainter)
    }


    fun bind(challenge: Challenge, clickListener: (Challenge) -> Unit) {
        titleTv?.text = challenge.title
        descriptionTv?.text = challenge.description
        activeUsersCountTv?.text = "${challenge.friendsCount} participantes"
        questionContainter?.setOnClickListener { clickListener(challenge) }

    }


}
