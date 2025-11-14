package com.example.projectironplate.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.projectironplate.R;
import com.example.projectironplate.databinding.FragmentDashboardBinding;
import com.example.projectironplate.utils.LineGraphMaker;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ImageView[] dots;
    private LinearLayout dotsLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setUpCalorieCounter(root, 2352, 2800);
        setUpMiniCards(root);
        setUpChartViewPager(root);

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
        View miniCards = inflater.inflate(R.layout.card_dash_goals, container, false);

        container.addView(miniCards);
    }

    private void setUpChartViewPager(View root) {
        LinearLayout container = root.findViewById(R.id.dashboard_container);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View chartContainer = inflater.inflate(R.layout.card_dash_graph_container, container, false);

        ViewPager2 viewPager = chartContainer.findViewById(R.id.viewPager_charts);
        dotsLayout = chartContainer.findViewById(R.id.dots_indicator);

        // Create chart data
        List<LineGraphMaker.ChartData> charts = new ArrayList<>();
        charts.add(new LineGraphMaker.ChartData(
                "Bodyweight",
                new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"},
                new float[]{75f, 75.5f, 74.8f, 75.2f, 74.9f, 74.7f, 74.5f}
        ));
        charts.add(new LineGraphMaker.ChartData(
                "Steps",
                new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"},
                new float[]{8000f, 10000f, 7500f, 9500f, 11000f, 8500f, 9000f}
        ));
        charts.add(new LineGraphMaker.ChartData(
                "Calories",
                new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"},
                new float[]{2200f, 2400f, 2100f, 2300f, 2500f, 2200f, 2300f}
        ));
        charts.add(new LineGraphMaker.ChartData(
                "Water",
                new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"},
                new float[]{2f, 1.8f, 1.9f, 2.2f, 1.6f, 2.3f, 2f}
        ));
        charts.add(new LineGraphMaker.ChartData(
                "Sleep",
                new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"},
                new float[]{8f, 7.4f, 8.2f, 9.3f, 10f, 6.7f, 7.8f}
        ));

        // Set up adapter
        int primaryColor = ContextCompat.getColor(requireContext(), R.color.accent);
        int whiteColor = ContextCompat.getColor(requireContext(), R.color.white);
        LineGraphMaker adapter = new LineGraphMaker(charts, primaryColor, whiteColor);
        viewPager.setAdapter(adapter);

        // Set up dots indicator
        setupDotsIndicator(charts.size());
        setCurrentIndicator(0);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                setCurrentIndicator(position);
            }
        });

        container.addView(chartContainer);
    }

    private void setupDotsIndicator(int count) {
        dots = new ImageView[count];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);

        for (int i = 0; i < count; i++) {
            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.dot_inactive));
            dots[i].setLayoutParams(layoutParams);
            dotsLayout.addView(dots[i]);
        }
    }

    private void setCurrentIndicator(int position) {
        for (int i = 0; i < dots.length; i++) {
            int drawableId = (i == position) ? R.drawable.dot_active : R.drawable.dot_inactive;
            dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext(), drawableId));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}