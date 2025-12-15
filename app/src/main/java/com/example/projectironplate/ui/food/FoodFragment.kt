package com.example.projectironplate.ui.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.projectironplate.R
import com.example.projectironplate.databinding.FragmentFoodBinding
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlin.math.max
import kotlin.math.min

class FoodFragment : Fragment() {
    private var binding: FragmentFoodBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val foodViewModel =
            ViewModelProvider(this).get<FoodViewModel>(FoodViewModel::class.java)

        binding = FragmentFoodBinding.inflate(inflater, container, false)
        val root: View = binding!!.getRoot()

        setUpCalorieCounter(root, 2352, 2800)
        setUpMacroCounter(root, 135, 220, 25, 100, 184, 265)
        addMeal(root)

        return root
    }

    private fun setUpCalorieCounter(root: View, remainingCals: Int, goalCals: Int) {
        val container = root.findViewById<LinearLayout>(R.id.food_container)

        val inflater = LayoutInflater.from(getContext())
        val calorieCard = inflater.inflate(R.layout.card_food_calories, container, false)

        val percent = macroCalculator(remainingCals, goalCals)

        val remainingCalsText = calorieCard.findViewById<TextView>(R.id.tv_remaining_cals)
        remainingCalsText.setText(remainingCals.toString())

        val progress = calorieCard.findViewById<CircularProgressIndicator>(R.id.calorie_progress)
        progress.setProgress(percent)

        container.addView(calorieCard)
    }

    private fun setUpMacroCounter(
        root: View,
        remainingProtein: Int,
        goalProtein: Int,
        remainingFat: Int,
        goalFat: Int,
        remainingCarbs: Int,
        goalCarbs: Int
    ) {
        val container = root.findViewById<LinearLayout>(R.id.food_container)

        val inflater = LayoutInflater.from(getContext())
        val macroCard = inflater.inflate(R.layout.card_food_macros, container, false)

        var percent = macroCalculator(remainingProtein, goalProtein)

        val remainingProteinText = macroCard.findViewById<TextView>(R.id.tv_remaining_protein)
        remainingProteinText.setText(remainingProtein.toString())

        val proteinProgress =
            macroCard.findViewById<CircularProgressIndicator>(R.id.protein_progress)
        proteinProgress.setProgress(percent)


        percent = macroCalculator(remainingFat, goalFat)

        val remainingFatText = macroCard.findViewById<TextView>(R.id.tv_remaining_fat)
        remainingFatText.setText(remainingFat.toString())

        val fatProgress = macroCard.findViewById<CircularProgressIndicator>(R.id.fat_progress)
        fatProgress.setProgress(percent)


        percent = macroCalculator(remainingCarbs, goalCarbs)

        val remainingCarbText = macroCard.findViewById<TextView>(R.id.tv_remaining_carbs)
        remainingCarbText.setText(remainingCarbs.toString())

        val carbProgress = macroCard.findViewById<CircularProgressIndicator>(R.id.carbs_progress)
        carbProgress.setProgress(percent)

        container.addView(macroCard)
    }

    private fun macroCalculator(remaining: Int, goal: Int): Int {
        val consumed = goal - remaining
        val percentF = if (goal > 0) (consumed * 100f) / goal else 0f
        val percent = Math.round(max(0f, min(100f, percentF)))

        return percent
    }

    private fun addMeal(root: View) {
        val container = root.findViewById<LinearLayout>(R.id.food_container)

        val inflater = LayoutInflater.from(getContext())
        val mealCard = inflater.inflate(R.layout.card_food_add_meal, container, false)

        container.addView(mealCard)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}