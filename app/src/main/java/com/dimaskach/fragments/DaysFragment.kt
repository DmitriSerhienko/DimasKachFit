package com.dimaskach.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimaskach.R
import com.dimaskach.adapters.DayModel
import com.dimaskach.adapters.DaysAdapter
import com.dimaskach.adapters.ExerciseModel
import com.dimaskach.databinding.FragmentDaysBinding
import com.dimaskach.utils.FragmentManager
import com.dimaskach.utils.MainViewModel


class DaysFragment : Fragment(), DaysAdapter.Listener {
    private lateinit var binding: FragmentDaysBinding
    private val model: MainViewModel by activityViewModels()
    private var ab: ActionBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
    }


    private fun initRcView() = with(binding) {
        val adapter = DaysAdapter(this@DaysFragment)
        ab = (activity as AppCompatActivity).supportActionBar
        ab?.title = getString(R.string.days)
        rcViewDays.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
        rcViewDays.adapter = adapter
        adapter.submitList(fillDaysArray())
    }

    private fun fillDaysArray(): ArrayList<DayModel> {
        val tArray = ArrayList<DayModel>()
        var daysDoneCounter = 0
        resources.getStringArray(R.array.day_exercises).forEach {
            model.currentDay++
            val exCounter = it.split(",").size

            tArray.add(DayModel(it, 0, model.getExerciseCount() == exCounter))
        }
        binding.pBar.max = tArray.size
        tArray.forEach{
            if(it.isDone) daysDoneCounter++
        }
        updateRestDays(tArray.size - daysDoneCounter, tArray.size)
        return tArray

    }

    private fun updateRestDays(restDays : Int, days: Int) = with(binding){

        val rDays = getString(R.string.rest) + " $restDays " + getString(R.string.rest_days)
        tvRestDays.text = rDays
        pBar.progress = days - restDays
    }

    private fun fillExerciseList(day: DayModel) {
        val tempList = ArrayList<ExerciseModel>()
        day.exercises.split(",").forEach {
            val exerciseList = resources.getStringArray(R.array.exercise)
            val exercise = exerciseList[it.toInt()]
            val exerciseArray = exercise.split("|")
            tempList.add(ExerciseModel(exerciseArray[0], exerciseArray[1], false, exerciseArray[2]))
        }
        model.mutableListExercise.value = tempList
    }

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }

    override fun onClick(day: DayModel) {
        fillExerciseList(day)
        model.currentDay = day.dayNumber
        FragmentManager.setFragment(ExerciseListFragment.newInstance(),
            activity as AppCompatActivity)
    }


}