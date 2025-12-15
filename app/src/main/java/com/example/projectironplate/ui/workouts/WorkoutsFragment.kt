package com.example.projectironplate.ui.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.projectironplate.databinding.FragmentWorkoutsBinding

class WorkoutsFragment : Fragment() {
    private var binding: FragmentWorkoutsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val workoutsViewModel =
            ViewModelProvider(this).get<WorkoutsViewModel>(WorkoutsViewModel::class.java)

        binding = FragmentWorkoutsBinding.inflate(inflater, container, false)
        val root: View = binding!!.getRoot()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}