package com.tiagosantos.weatherapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import com.tiagosantos.weatherapp.R
import com.tiagosantos.weatherapp.models.CurrentWeather
import com.tiagosantos.weatherapp.models.TimelineAttributes


class TimeLineAdapter(private val mFeedList: List<CurrentWeather>, private var mAttributes: TimelineAttributes, private val ctx: Context) : RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder>() {

    override fun onBindViewHolder(holder: TimeLineViewHolder, position: Int) {

        val timeLineModel = mFeedList[position]

        if (timeLineModel.id.isNullOrEmpty()) {
            holder.itemView.findViewById<TextView>(R.id.cityName).text = timeLineModel.id
        }else{
            holder.itemView.isGone
        }

        //Adjusts weather icon accordingly to the weather code extracted from the API response
        when (timeLineModel.currentWeather[position].iconCode) {
            "01d", "02d" -> R.drawable.sun
            else -> throw Error("Invalid code!")
        }

    }

    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }

    override fun getItemCount() = mFeedList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineAdapter.TimeLineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
        )
        return TimeLineViewHolder(view)
    }

    inner class TimeLineViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {

        //val cityName = itemView.text_timeline_cityName
        //val temp = itemView.text_timeline_temp
        private val timeline = itemView.timeline

        init {
            timeline.initLine(viewType)
            timeline.setMarkerColor(mAttributes.markerColor)
            timeline.setStartLineColor(mAttributes.startLineColor, viewType)
            timeline.setEndLineColor(mAttributes.endLineColor, viewType)
        }
    }

    data class ItemValues(
            val id: String,
            val cityName: String,
            val temperature: String,
            val iconCode: String
    )


}