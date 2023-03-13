package com.redoz.rockpaperscissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import kotlin.random.Random

enum class Move {
    rock,
    paper,
    scissors
}

class MainActivity : AppCompatActivity() {
    lateinit var txtViewStatus: TextView
    lateinit var txtViewUser: TextView
    lateinit var txtViewMachine: TextView
    lateinit var btnRock: MaterialButton
    lateinit var btnPaper: MaterialButton
    lateinit var btnScissors: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtViewStatus = findViewById(R.id.txtViewStatus)
        txtViewUser = findViewById(R.id.txtViewUser)
        txtViewMachine = findViewById(R.id.txtViewMachine)
        btnRock = findViewById(R.id.rockButton)
        btnPaper = findViewById(R.id.paperButton)
        btnScissors = findViewById(R.id.scissorsButton)


        btnRock.setOnClickListener { playRound(Move.rock) }
        btnPaper.setOnClickListener { playRound(Move.paper) }
        btnScissors.setOnClickListener { playRound(Move.scissors) }

    }

    fun playRound(playerMove: Move) {
        val machineMove = getMachineMove()
        val winner = getWinner(playerMove.toString(), machineMove)
        updateScreen(winner, playerMove.toString(), machineMove)
    }

    fun getMachineMove(): String {
        val options = listOf<String>("rock", "paper", "scissors")

        return options[Random.nextInt(0, 3)]
    }

    fun getWinner(playerMove: String, machineMove: String): String {
        return when {
            playerMove == machineMove -> {
                "Tie"
            }
            playerMove == "rock" && machineMove == "scissors" ||
                    playerMove == "paper" && machineMove == "rock" ||
                    playerMove == "scissors" && machineMove == "paper" -> {
                "User"
            }
            else -> {
                "Machine"
            }
        }
    }

    fun updateScreen(winner: String, userMove:String, machineMove: String) {
        txtViewMachine.text = machineMove
        txtViewUser.text = userMove
        when (winner) {
            "User" -> {
                txtViewStatus.text = "YOU WIN"
            }
            "Tie" -> {
                txtViewStatus.text = "TIE"
            }
            "Machine" -> {
                txtViewStatus.text = "YOU LOSE"
            }
        }
    }
}
