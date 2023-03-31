package com.mirsalim.managing_checklist.ui.data

import java.io.Serializable

data class Fruit(
    var isChecked:Boolean = false,
    val name:String
): Serializable