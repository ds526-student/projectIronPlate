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

        setUpCalorieCounter(root, 100, 200);

        return root;
    }

    private void setUpCalorieCounter(View root, int remainingCals, int goalCals) {
        LinearLayout container = root.findViewById(R.id.food_container);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View calorieCard = inflater.inflate(R.layout.card_food_calories, container, false);

        float percentF = (goalCals > 0) ? (remainingCals * 100f) / goalCals : 0f;
        int percent = Math.round(Math.max(0f, Math.min(100f, percentF)));

        TextView remainingCalsText = calorieCard.findViewById(R.id.tv_remaining_cals);
        remainingCalsText.setText(String.valueOf(remainingCals));

        CircularProgressIndicator progress = calorieCard.findViewById(R.id.calorie_progress);
        progress.setProgress(percent);

        container.addView(calorieCard);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}