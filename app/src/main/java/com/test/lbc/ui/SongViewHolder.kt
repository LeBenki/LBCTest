package com.test.lbc.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.test.lbc.R
import com.test.lbc.model.Song

class SongViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.song_item, parent, false)
) {

    private val nameView = itemView.findViewById<TextView>(R.id.name)
    private val thumbnail = itemView.findViewById<ImageView>(R.id.thumbnail)

    fun bindTo(song: Song?) {
        if (song != null) {
            nameView.text = song.title

            val url = GlideUrl(
                song.thumbnailUrl, LazyHeaders.Builder()
                    .addHeader("User-Agent", "your-user-agent")
                    .build()
            )
            Glide
                .with(itemView)
                .load(url)
                .centerCrop()
                .into(thumbnail)
        }
    }
}
