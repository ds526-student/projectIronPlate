package com.example.projectironplate.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectironplate.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class LineGraphMaker extends RecyclerView.Adapter<LineGraphMaker.ChartViewHolder> {

    private List<ChartData> charts;
    private int primaryColor;
    private int whiteColor;

    public static class ChartData {
        String title;
        String[] labels;
        float[] values;

        public ChartData(String title, String[] labels, float[] values) {
            this.title = title;
            this.labels = labels;
            this.values = values;
        }
    }

    public LineGraphMaker(List<ChartData> charts, int primaryColor, int whiteColor) {
        this.charts = charts;
        this.primaryColor = primaryColor;
        this.whiteColor = whiteColor;
    }

    @NonNull
    @Override
    public ChartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_dash_graph_line, parent, false);
        return new ChartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChartViewHolder holder, int position) {
        ChartData chartData = charts.get(position);
        holder.bind(chartData, primaryColor, whiteColor);
    }

    @Override
    public int getItemCount() {
        return charts.size();
    }

    static class ChartViewHolder extends RecyclerView.ViewHolder {
        TextView tvChartTitle;
        LineChart lineChart;

        public ChartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChartTitle = itemView.findViewById(R.id.tv_chart_title);
            lineChart = itemView.findViewById(R.id.line_chart);
        }

        public void bind(ChartData chartData, int primaryColor, int whiteColor) {
            tvChartTitle.setText(chartData.title);

            // Create entries
            List<Entry> entries = new ArrayList<>();
            for (int i = 0; i < chartData.values.length; i++) {
                entries.add(new Entry(i, chartData.values[i]));
            }

            // Make dataset
            LineDataSet dataSet = new LineDataSet(entries, chartData.title);
            dataSet.setColor(primaryColor);
            dataSet.setCircleColor(primaryColor);
            dataSet.setLineWidth(3f);
            dataSet.setCircleRadius(5f);
            dataSet.setValueTextColor(whiteColor);
            dataSet.setValueTextSize(12f);

            // Add data
            LineData lineData = new LineData(dataSet);

            // Config
            lineChart.setData(lineData);
            lineChart.getDescription().setEnabled(false);
            lineChart.getAxisLeft().setTextColor(whiteColor);
            lineChart.getAxisRight().setEnabled(false);

            if (chartData.labels != null) {
                lineChart.getXAxis().setEnabled(true);
                lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(chartData.labels));
                lineChart.getXAxis().setTextColor(whiteColor);
                lineChart.getXAxis().setGranularity(1f);
                lineChart.getXAxis().setDrawGridLines(false);
            } else {
                lineChart.getXAxis().setEnabled(false);
            }

            lineChart.getLegend().setEnabled(false);
            lineChart.invalidate();
        }
    }
}