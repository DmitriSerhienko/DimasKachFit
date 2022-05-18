package com.dimaskach.utils

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.dimaskach.adapters.ExerciseModel

class MainViewModel : ViewModel(){
    val mutableListExercise = MediatorLiveData<ArrayList<ExerciseModel>>()

}