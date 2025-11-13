package com.example.projectironplate.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projectironplate.R;
import com.example.projectironplate.databinding.FragmentDashboardBinding;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setUpCalorieCounter(root, 2352, 2800);
        setUpMiniCards(root);
        setUpLineChart(root, "Bodyweight", new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"}, new float[]{0f, 0f, 0f, 0f, 0f, 0f, 0f});

        return root;
    }

    private void setUpCalorieCounter(View root, int remainingCals, int goalCals) {
        LinearLayout container = root.findViewById(R.id.dashboard_container);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View calorieCard = inflater.inflate(R.layout.card_food_calories, container, false);

        int percent = macroCalculator(remainingCals, goalCals);

        TextView remainingCalsText = calorieCard.findViewById(R.id.tv_remaining_cals);
        remainingCalsText.setText(String.valueOf(remainingCals));

        CircularProgressIndicator progress = calorieCard.findViewById(R.id.calorie_progress);
        progress.setProgress(percent);

        container.addView(calorieCard);
    }

    private int macroCalculator(int remaining, int goal) {
        int consumed = goal - remaining;
        float percentF = (goal > 0) ? (consumed * 100f) / goal : 0f;
        int percent = Math.round(Math.max(0f, Math.min(100f, percentF)));

        return percent;
    }

    private void setUpMiniCards(View root) {
        LinearLayout container = root.findViewById(R.id.dashboard_container);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View miniCards = inflater.inflate(R.layout.card_dash_small_many, container, false);

        container.addView(miniCards);
    }


    private void setUpLineChart(View root, String chartTitle, String[] labels, float[] values) {
        LinearLayout container = root.findViewById(R.id.dashboard_container);

        // inflate line chart card layout
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View lineGraphCard = inflater.inflate(R.layout.card_dash_graph_line, null);

        // set title
        TextView tvChartTitle = lineGraphCard.findViewById(R.id.tv_chart_title);
        tvChartTitle.setText(chartTitle);

        LineChart lineChart = lineGraphCard.findViewById(R.id.line_chart);

        // create entries
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            entries.add(new Entry(i, values[i]));
        }

        // make dataset
        LineDataSet dataSet = new LineDataSet(entries, chartTitle);
        dataSet.setColor(getResources().getColor(R.color.primary));
        dataSet.setCircleColor(getResources().getColor(R.color.primary));
        dataSet.setLineWidth(3f);
        dataSet.setCircleRadius(5f);
        dataSet.setValueTextColor(getResources().getColor(R.color.white));
        dataSet.setValueTextSize(12f);

        // adds data
        LineData lineData = new LineData(dataSet);

        // config
        lineChart.setData(lineData);
        lineChart.getDescription().setEnabled(false);
        lineChart.getAxisLeft().setTextColor(getResources().getColor(R.color.white));
        lineChart.getAxisRight().setEnabled(false);

        if (labels != null) {
            lineChart.getXAxis().setEnabled(true);
            lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
            lineChart.getXAxis().setTextColor(getResources().getColor(R.color.white));
            lineChart.getXAxis().setGranularity(1f);
            lineChart.getXAxis().setDrawGridLines(false);
        } else {
            lineChart.getXAxis().setEnabled(false);
        }

        lineChart.getLegend().setEnabled(false);
        lineChart.invalidate();

        // add to container
        container.addView(lineGraphCard);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}