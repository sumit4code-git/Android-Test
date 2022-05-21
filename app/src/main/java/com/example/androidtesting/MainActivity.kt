package com.example.androidtesting

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity(),KeyboardView.OnKeyBoardEvent {
    private lateinit var keyboardViewFragment:KeyboardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et.isFocusable = false
        keyboardViewFragment=KeyboardView()
        et.setOnClickListener {
            if(keyboardViewFragment==null)
            {
                keyboardViewFragment=KeyboardView.newInstance(et.text.toString())
                supportFragmentManager.beginTransaction().add(R.id.Key_board_container, keyboardViewFragment).commit()
            }
            else
            {
                if(keyboardViewFragment.isVisible)
                    supportFragmentManager.beginTransaction().hide(keyboardViewFragment).commit()
                else
                {
                    keyboardViewFragment=KeyboardView.newInstance(et.text.toString())
                    supportFragmentManager.beginTransaction().add(R.id.Key_board_container, keyboardViewFragment).commit()
                }
            }
        }
    }
    override fun numberIsPressed(total: String?) {
        et.setText(total)
    }

    override fun doneButtonPressed(total: String?) {
        et.setText(total)
        if(keyboardViewFragment.isVisible)
            supportFragmentManager.beginTransaction().hide(keyboardViewFragment).commit()
    }
    override fun backButtonPressed(total: String?) {
        et.setText(total)
    }

    override fun backLongPressed() {
        et.setText("")
    }
}
