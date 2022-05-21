package com.example.androidtesting

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.keyboard.view.*


class KeyboardView : Fragment() {
    private var sb: StringBuilder? = null
    private var keyboardEventListener: OnKeyBoardEvent? = null
    var maxLength = 15
    private var currentLength = 0
    @Deprecated("Deprecated in Java")
    override fun onAttach(activity: Activity) {
        try {
            keyboardEventListener = activity as OnKeyBoardEvent
        } catch (e: ClassCastException) {
            Log.e(
                "ClassCastException in KeyBoardFragment row 50",
                "$activity must implement onKeyboardEvent"
            )
            e.printStackTrace()
        }
        super.onAttach(activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sb = StringBuilder(arguments?.getString("et_value"))
        currentLength = sb!!.length
        val rootView: View = inflater.inflate(R.layout.keyboard, container, false)
        addListeners(rootView)
        return rootView
    }

    private fun addListeners(rootView:View) {
        rootView.t9_key_1.setOnClickListener {
            add((it as TextView).text.toString()) }
        rootView.t9_key_2.setOnClickListener {
            add((it as TextView).text.toString()) }
        rootView.t9_key_3.setOnClickListener {
            add((it as TextView).text.toString()) }
        rootView.t9_key_4.setOnClickListener {
            add((it as TextView).text.toString()) }
        rootView.t9_key_5.setOnClickListener {
            add((it as TextView).text.toString()) }
        rootView.t9_key_6.setOnClickListener {
            add((it as TextView).text.toString()) }
        rootView.t9_key_7.setOnClickListener {
            add((it as TextView).text.toString()) }
        rootView.t9_key_8.setOnClickListener {
            add((it as TextView).text.toString()) }
        rootView.t9_key_9.setOnClickListener {
            add((it as TextView).text.toString()) }
        rootView.t9_key_0.setOnClickListener {
            add((it as TextView).text.toString()) }
        rootView.t9_key_0.setOnClickListener {
            add((it as TextView).text.toString()) }
        rootView.t9_key_clear.setOnClickListener {
            if (sb!!.isNotEmpty()) {
                currentLength--
                sb!!.deleteCharAt(sb!!.length - 1)
                keyboardEventListener!!.backButtonPressed(sb.toString())
            } }
        rootView.t9_key_clear.setOnLongClickListener{
            currentLength = 0
            sb = java.lang.StringBuilder()
            keyboardEventListener!!.backLongPressed()
            return@setOnLongClickListener true
        }
        rootView.t9_key_done.setOnClickListener {
            keyboardEventListener!!.doneButtonPressed(
                sb.toString()
            )
        }
    }


    interface OnKeyBoardEvent {
        fun numberIsPressed(total: String?)
        fun doneButtonPressed(total: String?)
        fun backButtonPressed(total: String?)
        fun backLongPressed()
    }

    private fun add(num: String?) {
        currentLength++
        if (currentLength <= maxLength) {
            sb!!.append(num)
            keyboardEventListener!!.numberIsPressed(sb.toString())
        } else currentLength--
    }

    companion object {
        fun newInstance(EditTextValue: String?): KeyboardView {
            val fragment = KeyboardView()
            val bundle = Bundle()
            bundle.putString("et_value", EditTextValue)
            fragment.arguments = bundle
            return fragment
        }
    }
}

