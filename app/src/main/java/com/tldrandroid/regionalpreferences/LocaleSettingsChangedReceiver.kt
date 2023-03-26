package com.tldrandroid.regionalpreferences

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class LocaleSettingsChangedReceiver : BroadcastReceiver() {
    interface Listener {
        fun onLocaleChanged()
    }

    private var listener: Listener? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        listener?.onLocaleChanged()
    }

    fun registerListener(listener: Listener) {
        this.listener = listener
    }

    fun unregisterListener() {
        listener = null
    }
}
