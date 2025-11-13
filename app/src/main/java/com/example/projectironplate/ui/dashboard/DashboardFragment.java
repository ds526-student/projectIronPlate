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
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setUpCalorieCounter(root, 2352, 2800);

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}