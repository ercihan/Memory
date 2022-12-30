package com.zhaw.memory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.zhaw.memory.databinding.FragmentFirstBinding
import kotlinx.coroutines.delay
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* Filling in HashMap */
        CentralObjects.cards.put(0, "⭐")
        CentralObjects.cards.put(1, "🌑")
        CentralObjects.cards.put(2, "📼")
        CentralObjects.cards.put(3, "☁")
        CentralObjects.cards.put(4, "✎")
        CentralObjects.cards.put(5, "🏠")
        CentralObjects.cards.put(6, "✈")
        CentralObjects.cards.put(7, "🦊")
        CentralObjects.cards.put(8, "📽")
        CentralObjects.cards.put(9, "💰")
        CentralObjects.cards.put(10, "🚲")
        CentralObjects.cards.put(11, "🛴")
        CentralObjects.cards.put(12, "📱")
        CentralObjects.cards.put(13, "⚙")
        CentralObjects.cards.put(14, "🌎")

        CentralObjects.playersName.add("Player A")
        CentralObjects.playersName.add("Player B")

        fillButtonsHashMap(view)
        createCardMapping()

        binding.card0.setOnClickListener {
            showEmojiIfAllowed(0)
        }

        binding.card1.setOnClickListener {
            showEmojiIfAllowed(1)
        }

        binding.card2.setOnClickListener {
            showEmojiIfAllowed(2)
        }

        binding.card3.setOnClickListener {
            showEmojiIfAllowed(3)
        }

        binding.card4.setOnClickListener {
            showEmojiIfAllowed(4)
        }

        binding.card5.setOnClickListener {
            showEmojiIfAllowed(5)
        }

        binding.card6.setOnClickListener {
            showEmojiIfAllowed(6)
        }

        binding.card7.setOnClickListener {
            showEmojiIfAllowed(7)
        }

        binding.card8.setOnClickListener {
            showEmojiIfAllowed(8)
        }

        binding.card9.setOnClickListener {
            showEmojiIfAllowed(9)
        }

        binding.card10.setOnClickListener {
            showEmojiIfAllowed(10)
        }

        binding.card11.setOnClickListener {
            showEmojiIfAllowed(11)
        }

        binding.card12.setOnClickListener {
            showEmojiIfAllowed(12)
        }

        binding.card13.setOnClickListener {
            showEmojiIfAllowed(13)
        }
        binding.card14.setOnClickListener {
            showEmojiIfAllowed(14)
        }

        binding.card15.setOnClickListener {
            showEmojiIfAllowed(15)
        }

        binding.card16.setOnClickListener {
            showEmojiIfAllowed(16)
        }

        binding.card17.setOnClickListener {
            showEmojiIfAllowed(17)
        }

        binding.card18.setOnClickListener {
            showEmojiIfAllowed(18)
        }

        binding.card19.setOnClickListener {
            showEmojiIfAllowed(19)
        }
        binding.card20.setOnClickListener {
            showEmojiIfAllowed(20)
        }

        binding.card21.setOnClickListener {
            showEmojiIfAllowed(21)
        }

        binding.card22.setOnClickListener {
            showEmojiIfAllowed(22)
        }

        binding.card23.setOnClickListener {
            showEmojiIfAllowed(23)
        }

        binding.card24.setOnClickListener {
            showEmojiIfAllowed(24)
        }

        binding.card25.setOnClickListener {
            showEmojiIfAllowed(25)
        }
        binding.card26.setOnClickListener {
            showEmojiIfAllowed(26)
        }

        binding.card27.setOnClickListener {
            showEmojiIfAllowed(27)
        }

        binding.card28.setOnClickListener {
            showEmojiIfAllowed(28)
        }

        binding.card29.setOnClickListener {
            showEmojiIfAllowed(29)
        }

        binding.player.setText(CentralObjects.playersName.get(0))
        binding.score.setText(CentralObjects.currentPointsPlayerA.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun fillButtonsHashMap(view: View){
        for(cardNr in 0..29) {
            val id = resources?.getIdentifier("card$cardNr", "id", context?.packageName)
            CentralObjects.buttons.put(cardNr, view.findViewById<Button>(id!!))
        }
    }

    fun createCardMapping(){
        var alreadyChoosen: HashMap<Int, Int> = HashMap()
        for(cardNr in 0..29){
            var randomNrOk : Boolean = true
            while(randomNrOk){
                var valueId: Int = Random.nextInt(0,15)
                if(alreadyChoosen.get(valueId) == null){
                    //How to setButton Text for later
                    //CentralObjects.buttons.get(cardNr)!!.setText(CentralObjects.cards.get(valueId))
                    if(cardNr == CentralObjects.cardMappings.size){
                        CentralObjects.cardMappings.add(CentralObjects.cards.get(valueId)!!)
                    }
                    alreadyChoosen.set(valueId, 1)
                    randomNrOk = false
                }
                else if(alreadyChoosen.get(valueId)!! < 2){
                    if(cardNr == CentralObjects.cardMappings.size){
                        CentralObjects.cardMappings.add(CentralObjects.cards.get(valueId)!!)
                    }
                    alreadyChoosen.set(valueId, alreadyChoosen.get(valueId)!! + 1)
                    randomNrOk = false
                }
            }
        }
    }

    private fun showEmojiIfAllowed(cardNr: Int){
        if(CentralObjects.currentUncoveredCards < 2){
            CentralObjects.buttons.get(cardNr)!!.setText(CentralObjects.cardMappings.get(cardNr))
            CentralObjects.currentUncoveredCards = CentralObjects.currentUncoveredCards + 1
            CentralObjects.currentOpenCards.put(cardNr, CentralObjects.cardMappings.get(cardNr))
            if(CentralObjects.currentUncoveredCards == 2){
                checkCardsEquality()
            }
        }
    }

    private fun checkCardsEquality(){
        if(CentralObjects.currentUncoveredCards == 2 && CentralObjects.currentOpenCards.size == 2){
            if(CentralObjects.currentOpenCards.get(0).equals(CentralObjects.currentOpenCards.get(1))){
                addPoint()
                //delay(6000)
                buttonDisappearForWholeGame()
                refreshPoints()
            }
            else{
                //Thread.sleep(500L)
                buttonDisappear()
                togglePlayer()
            }
        }
    }

    private fun addPoint(){
        if(CentralObjects.currentPlayer == 0){
            CentralObjects.currentPointsPlayerA = CentralObjects.currentPointsPlayerA + 1
        }
        else{
            CentralObjects.currentPointsPlayerB = CentralObjects.currentPointsPlayerB + 1
        }
    }

    private fun togglePlayer(){
        if(CentralObjects.currentPlayer == 0){
            CentralObjects.currentPlayer = 1
            binding.player.setText(CentralObjects.playersName.get(1))
            binding.score.setText(CentralObjects.currentPointsPlayerB.toString())
        }
        else{
            CentralObjects.currentPlayer = 0
            binding.player.setText(CentralObjects.playersName.get(0))
            binding.score.setText(CentralObjects.currentPointsPlayerA.toString())
        }
    }

    private fun refreshPoints(){
        if(CentralObjects.currentPlayer == 0){
            binding.score.setText(CentralObjects.currentPointsPlayerA.toString())
        }
        else{
            binding.score.setText(CentralObjects.currentPointsPlayerB.toString())
        }
    }

    private fun buttonDisappearForWholeGame(){
        if(CentralObjects.currentUncoveredCards == 2 && CentralObjects.currentOpenCards.size == 2){
            var keys = CentralObjects.currentOpenCards.keys
            keys.forEach{CentralObjects.buttons.get(it)?.visibility = View.INVISIBLE}
            CentralObjects.currentUncoveredCards = 0
            CentralObjects.currentOpenCards.clear()
            CentralObjects.buttonsDisappeard = CentralObjects.buttonsDisappeard + 2
            if(CentralObjects.buttonsDisappeard == 30){
                whoWon()
            }
        }
    }

    private fun buttonDisappear(){
        if(CentralObjects.currentUncoveredCards == 2 && CentralObjects.currentOpenCards.size == 2){
            var keys = CentralObjects.currentOpenCards.keys
            keys.forEach{CentralObjects.buttons.get(it)?.setText("")}
            CentralObjects.currentUncoveredCards = 0
            CentralObjects.currentOpenCards.clear()
        }
    }

    private fun whoWon(){
        if(CentralObjects.currentPointsPlayerA > CentralObjects.currentPointsPlayerB){
            binding.player.setText("Player A has won!")
            binding.score.setText(CentralObjects.currentPointsPlayerA.toString())
        }
        else if(CentralObjects.currentPointsPlayerA < CentralObjects.currentPointsPlayerB){
            binding.player.setText("Player B has won!")
            binding.score.setText(CentralObjects.currentPointsPlayerB.toString())
        }
        else{
            binding.player.setText("No player has won!")
            binding.score.setText("Points of Player A was: " + CentralObjects.currentPointsPlayerA.toString() + " ; Points of Player B was: " + CentralObjects.currentPointsPlayerB.toString())
        }
    }

}