/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding
import timber.log.Timber

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {
    // create reference to game view_model
private lateinit var gameViewModel :GameViewModel


    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )
        // bind view_model with fragment
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        Timber.i("get ViewModel")

        // register an observer which will observe a word and once data
        // is changed it will execute code in observer object
        gameViewModel.word.observe(this, Observer { newWord ->
            binding.wordText.text = newWord.toString()
        })

        gameViewModel.score.observe(this, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })

        gameViewModel.eventGameFinish.observe(this, Observer { hasFinished->
            if(hasFinished) {
                gameFinished()
                // since live_data knows about fragment lifecycle, after every configuration changes
                // since eventGameFinished is true the gameFinished method will be called
                // that is why after event happened we need to change our flag to false
                gameViewModel.onGameFinishComplete()
            }
        })

        gameViewModel.currentTime.observe(this, Observer { time ->
            binding.timerText.text = DateUtils.formatElapsedTime(time)
        })

        binding.correctButton.setOnClickListener {
            gameViewModel.onCorrect()
        }

        binding.skipButton.setOnClickListener {
            gameViewModel.onSkip()
        }

        return binding.root

    }

    /**
     * Resets the list of words and randomizes the order
     */


    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        // pass live_data and check for null
        Timber.i("+________________start navigation")
        val currentScore = gameViewModel.score.value ?: 0
        val action = GameFragmentDirections.actionGameToScore(currentScore)
        findNavController(this).navigate(action)
    }





}
