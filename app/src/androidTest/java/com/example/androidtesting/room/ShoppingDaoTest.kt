package com.example.androidtesting.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.androidtesting.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingDaoTest {

    @get:Rule
    var instantTaskExecutorRule=InstantTaskExecutorRule()

    private lateinit var database: ShoppingItemDatabase
    private lateinit var dao:ShoppingDao

    @Before
    fun setup(){
//inMemoryDatabaseBuilder-> it will store data in RAM not in persistance memory basically create dummy table
        database= Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingItemDatabase::class.java
        ).allowMainThreadQueries().build()
        dao=database.shoppingDao()
    }
    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertShoppingItemTest()= runBlockingTest {
        val shoppingItem= ShoppingItem("name",1,1f,"url",id=1)
        dao.insertShoppingItem(shoppingItem)

//        with getOrAwaitValue() we converting livedata list into normal list because tests must run synchronously one after other
        val allShoppingItems=dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).contains(shoppingItem)
    }

    @Test
    fun deleteShoppingItemTest()= runBlockingTest {
        val shoppingItem= ShoppingItem("name",1,1f,"url",id=1)
        dao.insertShoppingItem(shoppingItem)
        dao.deleteSHoppingItem(shoppingItem)

        val allShoppingItems=dao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItems).doesNotContain(shoppingItem)
    }

    @Test
    fun observeTotalPriceItemTest()= runBlockingTest {

        val shoppingItem= ShoppingItem("name",1,1f,"url",id=1)
        dao.insertShoppingItem(shoppingItem)
        val shoppingItem2= ShoppingItem("name",3,3f,"url",id=2)
        dao.insertShoppingItem(shoppingItem2)

        val totalPrice=dao.observeTotalPrice().getOrAwaitValue()
        assertThat(totalPrice).isEqualTo(10f)
    }
}