package com.dimaskach.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimaskach.R
import com.dimaskach.adapters.DayModel
import com.dimaskach.adapters.DaysAdapter
import com.dimaskach.adapters.ExerciseAdapter
import com.dimaskach.adapters.ExerciseModel
import com.dimaskach.databinding.ExerciseBinding
import com.dimaskach.databinding.ExercisesListFragmentBinding
import com.dimaskach.databinding.FragmentDaysBinding
import com.dimaskach.utils.FragmentManager
import com.dimaskach.utils.MainViewModel
import pl.droidsonroids.gif.GifDrawable


class ExerciseFragment : Fragment() {
    private lateinit var binding: ExerciseBinding
    private var exerciseCounter = 0
    private var exList: ArrayList<ExerciseModel>? = null
    private val model: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = ExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.mutableListExercise.observe(viewLifecycleOwner) {
            exList = it
            nextExercise()
        }
        binding.butNext.setOnClickListener {
            nextExercise()
        }
    }

    private fun nextExercise() {
        if (exerciseCounter < exList?.size!!) {
            val ex = exList?.get(exerciseCounter++) ?: return
            showExercise(ex)
        } else {
            Toast.makeText(activity, "Done", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showExercise(exercise: ExerciseModel) = with(binding){
        imMain.setImageResource(R.drawable.fitnes_bg_black)
       // imMain.setImageDrawable(GifDrawable(root.context.assets, exercise.image))
        tvName.text = exercise.name
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExerciseFragment()
    }


}