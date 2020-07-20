package com.analogpresent.pwgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val numbers = "0123456789"
        val charsSmall = "abcdefghijklmnopqrstuvwxyz"
        val charsCapital = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val charsSymbols = "!#$%&*+,-.:<=>?@~" // not all special chars!
        var PasswordLength = 8

        seekBar?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                PasswordLength = seekBar.progress
                textView3.setText(PasswordLength.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {           }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {            }
        })

        button.setOnClickListener {

            // check what was checked!
            var PasswordLetters = ""
            if (chkBox_num.isChecked)
                PasswordLetters += numbers;

            if (chkBox_small.isChecked)
                PasswordLetters += charsSmall;

            if (chkBox_Capital.isChecked)
                PasswordLetters += charsCapital;

            if (PasswordLetters.equals(""))
                Toast.makeText(this@MainActivity, "Please select something! ", Toast.LENGTH_SHORT)
                    .show()
            else {
                val tempPassword = generatePassword(PasswordLetters, PasswordLength)
                textView.setText(tempPassword)

                // clipboard
                var clip = ClipData.newPlainText("...", tempPassword)
//                clipboard.setPrimaryClip(clip)
//                Toast.makeText(this@MainActivity, "Copied: " + tempPassword, Toast.LENGTH_SHORT)
//                    .show()
            }
        }
    }
}

private fun generatePassword(passwordLetters: String, passwordLength: Int): String {
    var tempPassword = ""
//        val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9') + ('?')
//        fun randomID(): String = List(8) { alphabet.random() }.joinToString("")

    for (x in 1..passwordLength)
        tempPassword += passwordLetters.random().toString()

    return tempPassword
}

