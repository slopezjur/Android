package com.example.diceroller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.roll_button)
        val checkBox: CheckBox = findViewById(R.id.checkbox)

        rollButton.setOnClickListener {
            rollDice()

            Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()

            if(checkBox.isChecked) {
                rollButton.text = "Let's roll"
                checkBox.isChecked = false
            } else {
                rollButton.text = "1"
                checkBox.isChecked = true
            }

        }
    }

    private fun rollDice() {

        val randomInt = Random().nextInt(6) + 1
        val drawableResource = when (randomInt){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        val diceImage: ImageView = findViewById(R.id.dice_image)
        diceImage.setImageResource(drawableResource)
    }
}
