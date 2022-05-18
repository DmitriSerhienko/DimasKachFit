package com.dimaskach.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimaskach.R
import com.dimaskach.adapters.DayModel
import com.dimaskach.adapters.DaysAdapter
import com.dimaskach.adapters.ExerciseAdapter
import com.dimaskach.databinding.ExercisesListFragmentBinding
import com.dimaskach.databinding.FragmentDaysBinding
import com.dimaskach.utils.FragmentManager
import com.dimaskach.utils.MainViewModel


class ExerciseListFragment : Fragment() {
    private lateinit var binding: ExercisesListFragmentBinding
    private lateinit var adapter: ExerciseAdapter
    private val model : MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = ExercisesListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        model.mutableListExercise.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    private fun init() = with(binding) {
        adapter = ExerciseAdapter()
        rcViewEx.layoutManager = LinearLayoutManager(activity)
        rcViewEx.adapter = adapter
        bStart.setOnClickListener {
            FragmentManager.setFragment(WaitingFragment.newInstance(),
                activity as AppCompatActivity)
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = ExerciseListFragment()
    }




}