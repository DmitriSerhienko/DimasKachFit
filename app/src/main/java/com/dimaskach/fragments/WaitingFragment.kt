package com.dimaskach.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dimaskach.R
import com.dimaskach.databinding.WaitingFragmentBinding
import com.dimaskach.utils.FragmentManager
import com.dimaskach.utils.TimeUtil


const val COUNT_DOWN_TIMER = 3000L

class WaitingFragment : Fragment() {
    private lateinit var binding: WaitingFragmentBinding
    private lateinit var timer: CountDownTimer
    private var ab: ActionBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = WaitingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ab = (activity as AppCompatActivity).supportActionBar
        ab?.title = getString(R.string.waiting)
        binding.progBar.max = COUNT_DOWN_TIMER.toInt()
        stareTimer()
    }

    private fun stareTimer() = with(binding) {
        timer = object : CountDownTimer(COUNT_DOWN_TIMER, 5) {
            override fun onTick(restTime: Long) {
                tvTimer.text = TimeUtil.getTime(restTime)
                progBar.progress = restTime.toInt()
            }

            override fun onFinish() {
                FragmentManager.setFragment(ExerciseFragment.newInstance(),
                    activity as AppCompatActivity)
            }
        }.start()
    }

    override fun onDetach() {
        super.onDetach()
        timer.cancel()
    }

    companion object {
        @JvmStatic
        fun newInstance() = WaitingFragment()
    }


}