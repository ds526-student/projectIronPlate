package com.example.projectironplate.ui.food;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projectironplate.R;
import com.example.projectironplate.databinding.FragmentFoodBinding;
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class FoodFragment extends Fragment {

    private FragmentFoodBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FoodViewModel foodViewModel =
                new ViewModelProvider(this).get(FoodViewModel.class);

        binding = FragmentFoodBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setUpCalorieCounter(root, 2352, 2800);
        setUpMacroCounter(root, 135, 220, 25, 100, 184, 265);
        addMeal(root);

        return root;
    }

    private void setUpCalorieCounter(View root, int remainingCals, int goalCals) {
        LinearLayout container = root.findViewById(R.id.food_container);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View calorieCard = inflater.inflate(R.layout.card_food_calories, container, false);

        int percent = macroCalculator(remainingCals, goalCals);

        TextView remainingCalsText = calorieCard.findViewById(R.id.tv_remaining_cals);
        remainingCalsText.setText(String.valueOf(remainingCals));

        CircularProgressIndicator progress = calorieCard.findViewById(R.id.calorie_progress);
        progress.setProgress(percent);

        container.addView(calorieCard);
    }

    private void setUpMacroCounter(View root, int remainingProtein, int goalProtein, int remainingFat, int goalFat, int remainingCarbs, int goalCarbs) {
        LinearLayout container = root.findViewById(R.id.food_container);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View macroCard = inflater.inflate(R.layout.card_food_macros, container, false);

        int percent = macroCalculator(remainingProtein, goalProtein);

        TextView remainingProteinText = macroCard.findViewById(R.id.tv_remaining_protein);
        remainingProteinText.setText(String.valueOf(remainingProtein));

        CircularProgressIndicator proteinProgress = macroCard.findViewById(R.id.protein_progress);
        proteinProgress.setProgress(percent);


        percent = macroCalculator(remainingFat, goalFat);

        TextView remainingFatText = macroCard.findViewById(R.id.tv_remaining_fat);
        remainingFatText.setText(String.valueOf(remainingFat));

        CircularProgressIndicator fatProgress = macroCard.findViewById(R.id.fat_progress);
        fatProgress.setProgress(percent);


        percent = macroCalculator(remainingCarbs, goalCarbs);

        TextView remainingCarbText = macroCard.findViewById(R.id.tv_remaining_carbs);
        remainingCarbText.setText(String.valueOf(remainingCarbs));

        CircularProgressIndicator carbProgress = macroCard.findViewById(R.id.carbs_progress);
        carbProgress.setProgress(percent);

        container.addView(macroCard);
    }

    private int macroCalculator(int remaining, int goal) {
        int consumed = goal - remaining;
        float percentF = (goal > 0) ? (consumed * 100f) / goal : 0f;
        int percent = Math.round(Math.max(0f, Math.min(100f, percentF)));

        return percent;
    }

    private void addMeal(View root) {
        LinearLayout container = root.findViewById(R.id.food_container);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View mealCard = inflater.inflate(R.layout.card_food_add_meal, container, false);

        container.addView(mealCard);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}