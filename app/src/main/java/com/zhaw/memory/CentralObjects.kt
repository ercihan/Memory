package com.zhaw.memory

import android.widget.Button

object CentralObjects {
    var cards:HashMap<Int, String> = HashMap<Int, String>()
    var buttons: HashMap<Int, Button> = HashMap()
    var cardMappings: ArrayList<String> = ArrayList()
    var currentUncoveredCards: Int = 0
    var currentOpenCards: HashMap<Int, String> = HashMap()
    var currentPointsPlayerA: Int = 0
    var currentPointsPlayerB: Int = 0
    var playersName : ArrayList<String> = ArrayList()
    var currentPlayer: Int = 0
    var buttonsDisappeard: Int = 0
}