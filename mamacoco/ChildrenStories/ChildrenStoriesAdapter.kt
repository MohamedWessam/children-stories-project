package com.mohamedwessam.mamacoco.ChildrenStories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.mohamedwessam.mamacoco.R

class ChildrenStoriesAdapter(var myArrayList: ArrayList<ChildrenStoriesRecyclerView>) :
    RecyclerView.Adapter<ChildrenStoriesAdapter.ViewHolder>() {

    lateinit var mClickListener: ClickListener

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.children_stories_list, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return myArrayList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        var infList = myArrayList[p1]
        p0.storyName.text = infList.mStoryName
        p0.storyDescription.text = infList.mStoryDescription
        p0.storyImage.setImageResource(infList.mStoryImage)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View) {
            mClickListener.onClick(adapterPosition, v)
        }

        val storyName = itemView.findViewById(R.id.txtStoryName) as TextView
        val storyDescription = itemView.findViewById(R.id.txtStoryDescription) as TextView
        val storyImage = itemView.findViewById(R.id.imageViewChildrenStories) as ImageView

        init {
            itemView.setOnClickListener(this)
        }
    }

    interface ClickListener {
        fun onClick(pos: Int, aView: View)
    }
}
