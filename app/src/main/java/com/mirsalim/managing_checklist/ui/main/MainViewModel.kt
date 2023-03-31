package com.mirsalim.managing_checklist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.mirsalim.managing_checklist.ui.data.Fruit
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    //apple, banana, pineapple, orange, grapefruit
    private val _fruitList = MutableLiveData<List<Fruit>>(
        listOf(
            Fruit(name = "apple"),
            Fruit(name = "banana"),
            Fruit(name = "pineapple"),
            Fruit(name = "orange"),
            Fruit(name = "grapefruit")
        )
    )

    val isAllSelected: LiveData<Boolean> = _fruitList.map {
        var isSelected = true
        it.map { item->
            isSelected = isSelected && item.isChecked
        }
        isSelected
    }

    val fruitsListText: LiveData<String> = _fruitList.map {
        var text = ""
        it.map {item->
            if(item.isChecked)
                text+="${item.name}, "
        }

        text
    }

    val fruitList: LiveData<List<Fruit>> = _fruitList

    fun updateList(item: Fruit){
        val fruits = fruitList.value!!.toMutableList().map {
            val newItem = it.copy(it.isChecked, it.name)
            newItem
        }

        val updatedFruits = fruits.map {
            if (it.name == item.name){
                it.isChecked = item.isChecked
            }
            it
        }

        _fruitList.value = updatedFruits
    }

    fun checkAll(isChecked: Boolean){
        val fruits = fruitList.value!!.toMutableList().map {
            val newItem = it.copy(it.isChecked, it.name)
            newItem
        }
        fruits.map {
            it.isChecked = isChecked
        }
        _fruitList.value = fruits
    }

    fun unCheckAll(){
        val fruits = fruitList.value!!.toMutableList().map {
            val newItem = it.copy(it.isChecked, it.name)
            newItem
        }
        fruits.map {
            it.isChecked = false
        }
        _fruitList.value = fruits
    }

}