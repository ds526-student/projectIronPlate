package com.example.projectironplate.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectironplate.R
import com.example.projectironplate.utils.LineGraphMaker.ChartViewHolder
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class LineGraphMaker(
    private val charts: MutableList<ChartData>,
    private val primaryColor: Int,
    private val whiteColor: Int
) : RecyclerView.Adapter<ChartViewHolder?>() {
    class ChartData(var title: String?, var labels: Array<String?>?, var values: FloatArray)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        val view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.card_dash_graph_line, parent, false)
        return ChartViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) {
        val chartData = charts.get(position)
        holder.bind(chartData, primaryColor, whiteColor)
    }

    override fun getItemCount(): Int {
        return charts.size
    }

    class ChartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvChartTitle: TextView
        var lineChart: LineChart

        init {
            tvChartTitle = itemView.findViewById<TextView>(R.id.tv_chart_title)
            lineChart = itemView.findViewById<LineChart>(R.id.line_chart)
        }

        fun bind(chartData: ChartData, primaryColor: Int, whiteColor: Int) {
            tvChartTitle.setText(chartData.title)

            // Create entries
            val entries: MutableList<Entry?> = ArrayList<Entry?>()
            for (i in chartData.values.indices) {
                entries.add(Entry(i.toFloat(), chartData.values[i]))
            }

            // Make dataset
            val dataSet = LineDataSet(entries, chartData.title)
            dataSet.setColor(primaryColor)
            dataSet.setCircleColor(primaryColor)
            dataSet.setLineWidth(3f)
            dataSet.setCircleRadius(5f)
            dataSet.setValueTextColor(whiteColor)
            dataSet.setValueTextSize(12f)

            // Add data
            val lineData = LineData(dataSet)

            // Config
            lineChart.setData(lineData)
            lineChart.getDescription().setEnabled(false)
            lineChart.getAxisLeft().setTextColor(whiteColor)
            lineChart.getAxisRight().setEnabled(false)

            if (chartData.labels != null) {
                lineChart.getXAxis().setEnabled(true)
                lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM)
                lineChart.getXAxis().setValueFormatter(IndexAxisValueFormatter(chartData.labels))
                lineChart.getXAxis().setTextColor(whiteColor)
                lineChart.getXAxis().setGranularity(1f)
                lineChart.getXAxis().setDrawGridLines(false)
            } else {
                lineChart.getXAxis().setEnabled(false)
            }

            lineChart.getLegend().setEnabled(false)
            lineChart.invalidate()
        }
    }
}