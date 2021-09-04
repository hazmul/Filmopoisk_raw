package com.ashush.filmopoisk_raw.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent


//может быть удален

class ActionBarVisibilityDispatcher(
    private var actionBar: androidx.appcompat.app.ActionBar?,
    private val temporaryVisibility: Mode = Mode.INVISIBLE,
    private val defaultVisibility: Mode = Mode.VISIBLE
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun setTemporaryVisibilityMode() {
        when (temporaryVisibility) {
            Mode.VISIBLE -> actionBar?.show()
            Mode.INVISIBLE -> actionBar?.hide()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun setDefaultVisibilityMode() {
        when (defaultVisibility) {
            Mode.VISIBLE -> actionBar?.show()
            Mode.INVISIBLE -> actionBar?.hide()
        }
    }

    enum class Mode {
        VISIBLE, INVISIBLE
    }
}