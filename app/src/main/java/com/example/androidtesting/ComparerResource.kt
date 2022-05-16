package com.example.androidtesting

import android.content.Context

class ComparerResource {
    fun equal(Context:Context,resId:Int,string:String): Boolean {
        return Context.getString(resId
        )==string
    }
}