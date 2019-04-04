package com.example.android.guesstheword.screens.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel : ViewModel(){

    // Make word/score as a live data object
    // encapsulate lave_data, it means that only ViewModel class can modify data, externally we can
    // call only get method on data
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score
    // register an event that shows that the game is finished
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
             get() = _eventGameFinish

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init{

        Timber.i("created VieModel")
        // since liveData object is null at the beginning we need to initialize it via getValue method
        _score.value = 0;
        _eventGameFinish.value = false
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
           _eventGameFinish.value = true
        } else {
            // initialize word
            _word.value= wordList.removeAt(0)
        }

    }

     fun onSkip() {
         // update score object, we need to check it for nullable, since livedata is a nullable object
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

     fun onCorrect() {
         // update score object, we need to check it for nullable, since livedata is a nullable object
         _score.value = (score.value)?.plus(1)
        nextWord()
    }
    override fun onCleared() {
        super.onCleared()
        Timber.i("destroyed ViewModel")
    }


    fun onGameFinishComplete(){
        _eventGameFinish.value = false
    }


}

