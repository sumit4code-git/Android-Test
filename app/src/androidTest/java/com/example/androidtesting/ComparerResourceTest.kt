package com.example.androidtesting

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class ComparerResourceTest : TestCase() {
    private lateinit var compareResource:ComparerResource
//    Before block runs before each test case runs and here it will initialize the object again ach time
    @Before
    public override fun setUp() {
        super.setUp()
        compareResource= ComparerResource()
    }

    public override fun tearDown() {}
   @Test
    fun testEqualTrue() {
        val context=ApplicationProvider.getApplicationContext<Context>()
       val result= compareResource.equal(context,R.string.app_name,"Android Testing")
        assertTrue(result)
    }
    @Test
    fun testEqualFalse() {
        val context=ApplicationProvider.getApplicationContext<Context>()
        val result= compareResource.equal(context,R.string.app_name,"Android sting")
        assertFalse(result)
    }
}