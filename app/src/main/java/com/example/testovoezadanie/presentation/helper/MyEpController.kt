package com.example.testovoezadanie.presentation.helper

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.example.testovoezadanie.presentation.fragment.MainFragmentState

class MyEpController(private val context: Context): TypedEpoxyController<MainFragmentState>() {

    var callback: EpoxyController.() -> Unit = {}

    override fun buildModels(data: MainFragmentState?) {
        callback(this)
    }

    fun withModels(buildModels: EpoxyController.() -> Unit) {

        callback = buildModels
        setData(null)

    }




}