package com.example.projectironplate.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.projectironplate.R
import com.example.projectironplate.databinding.FragmentDashboardBinding
import com.example.projectironplate.utils.LineGraphMaker
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlin.math.max
import kotlin.math.min

class DashboardFragment : Fragment() {
    private var binding: FragmentDashboardBinding? = null
    private lateinit var dots: Array<ImageView?>
    private var dotsLayout: LinearLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get<DashboardViewModel>(DashboardViewModel::class.java)

        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding!!.getRoot()

        setUpCalorieCounter(root, 2352, 2800)
        setUpMiniCards(root)
        setUpChartViewPager(root)

        return root
    }

    private fun setUpCalorieCounter(root: View, remainingCals: Int, goalCals: Int) {
        val container = root.findViewById<LinearLayout>(R.id.dashboard_container)

        val inflater = LayoutInflater.from(getContext())
        val calorieCard = inflater.inflate(R.layout.card_food_calories, container, false)

        val percent = macroCalculator(remainingCals, goalCals)

        val remainingCalsText = calorieCard.findViewById<TextView>(R.id.tv_remaining_cals)
        remainingCalsText.setText(remainingCals.toString())

        val progress = calorieCard.findViewById<CircularProgressIndicator>(R.id.calorie_progress)
        progress.setProgress(percent)

        container.addView(calorieCard)
    }

    private fun macroCalculator(remaining: Int, goal: Int): Int {
        val consumed = goal - remaining
        val percentF = if (goal > 0) (consumed * 100f) / goal else 0f
        val percent = Math.round(max(0f, min(100f, percentF)))

        return percent
    }

    private fun setUpMiniCards(root: View) {
        val container = root.findViewById<LinearLayout>(R.id.dashboard_container)

        val inflater = LayoutInflater.from(getContext())
        val miniCards = inflater.inflate(R.layout.card_dash_goals, container, false)

        container.addView(miniCards)
    }

    private fun setUpChartViewPager(root: View) {
        val container = root.findViewById<LinearLayout>(R.id.dashboard_container)

        val inflater = LayoutInflater.from(getContext())
        val chartContainer = inflater.inflate(R.layout.card_dash_graph_container, container, false)

        val viewPager = chartContainer.findViewById<ViewPager2>(R.id.viewPager_charts)
        dotsLayout = chartContainer.findViewById<LinearLayout>(R.id.dots_indicator)

        // Create chart data
        val charts: MutableList<LineGraphMaker.ChartData?> = ArrayList<LineGraphMaker.ChartData?>()
        charts.add(
            LineGraphMaker.ChartData(
                "Bodyweight",
                arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
                floatArrayOf(75f, 75.5f, 74.8f, 75.2f, 74.9f, 74.7f, 74.5f)
            )
        )
        charts.add(
            LineGraphMaker.ChartData(
                "Steps",
                arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
                floatArrayOf(8000f, 10000f, 7500f, 9500f, 11000f, 8500f, 9000f)
            )
        )
        charts.add(
            LineGraphMaker.ChartData(
                "Calories",
                arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
                floatArrayOf(2200f, 2400f, 2100f, 2300f, 2500f, 2200f, 2300f)
            )
        )
        charts.add(
            LineGraphMaker.ChartData(
                "Water",
                arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
                floatArrayOf(2f, 1.8f, 1.9f, 2.2f, 1.6f, 2.3f, 2f)
            )
        )
        charts.add(
            LineGraphMaker.ChartData(
                "Sleep",
                arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
                floatArrayOf(8f, 7.4f, 8.2f, 9.3f, 10f, 6.7f, 7.8f)
            )
        )

        // Set up adapter
        val primaryColor = ContextCompat.getColor(requireContext(), R.color.accent)
        val whiteColor = ContextCompat.getColor(requireContext(), R.color.white)
        val adapter = LineGraphMaker(charts as MutableList<LineGraphMaker.ChartData>, primaryColor, whiteColor)
        viewPager.setAdapter(adapter)

        // Set up dots indicator
        setupDotsIndicator(charts.size)
        setCurrentIndicator(0)

        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setCurrentIndicator(position)
            }
        })

        container.addView(chartContainer)
    }

    private fun setupDotsIndicator(count: Int) {
        dots = arrayOfNulls<ImageView>(count)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)

        for (i in 0..<count) {
            dots[i] = ImageView(getContext())
            dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.dot_inactive
                )
            )
            dots[i]!!.setLayoutParams(layoutParams)
            dotsLayout!!.addView(dots[i])
        }
    }

    private fun setCurrentIndicator(position: Int) {
        for (i in dots.indices) {
            val drawableId = if (i == position) R.drawable.dot_active else R.drawable.dot_inactive
            dots[i]!!.setImageDrawable(ContextCompat.getDrawable(requireContext(), drawableId))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}