package com.example.android.guesstheword.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(fimalScore: Int): ViewModel() {

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    // register an event that shows that the game is finished
    private val _eventPlayEgain = MutableLiveData<Boolean>()
    val eventPlayEgain: LiveData<Boolean>
        get() = _eventPlayEgain

    init {
       _score.value = fimalScore
        _eventPlayEgain.value = false;
    }

     fun onPlayAgain() {
        _eventPlayEgain.value = true

    }

    fun onPlayEgainComplete() {
        _eventPlayEgain.value = false
    }
}