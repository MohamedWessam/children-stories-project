package com.mohamedwessam.mamacoco.AplaFadelaStories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mohamedwessam.mamacoco.R

class ChildrenFadelaAdapter(var myArrayList: ArrayList<ChildrenFadelaRecyclerView>) :
    RecyclerView.Adapter<ChildrenFadelaAdapter.ViewHolder>() {

    lateinit var mClickListener: ClickListener

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.children_fadela_list, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return myArrayList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        var infList = myArrayList[p1]
        p0.storyName.text = infList.mStoryName
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View) {
            mClickListener.onClick(adapterPosition, v)
        }

        val storyName = itemView.findViewById(R.id.txt_audio_name) as TextView

        init {
            itemView.setOnClickListener(this)
        }
    }

    interface ClickListener {
        fun onClick(pos: Int, aView: View)
    }
}
