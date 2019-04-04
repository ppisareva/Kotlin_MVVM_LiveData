package com.example.android.guesstheword.screens.game

import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel : ViewModel(){

    // The current word
     var word = ""

    // The current score
     var score = 0


    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init{

        Timber.i("created VieModel")
        resetList()
        nextWord()
    }

    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        // randomly mixed words
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
           // gameFinished()
        } else {
            word = wordList.removeAt(0)
        }

    }

     fun onSkip() {
        score--
        nextWord()
    }

     fun onCorrect() {
        score++
        nextWord()
    }
    override fun onCleared() {
        super.onCleared()
        Timber.i("destroyed ViewModel")
    }
}

