package com.example.androidtesting.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
//It Interact with database to perform CRUD operation called data Access Object
interface ShoppingDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Delete
    suspend fun deleteSHoppingItem(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_items ")
    //Not suspend fun  because it's returning live data object and room already works asynchronously
    fun observeAllShoppingItems():LiveData<List<ShoppingItem>>

    @Query("SELECT SUM(price * amount) FROM shopping_items")
    fun observeTotalPrice():LiveData<Float>



}