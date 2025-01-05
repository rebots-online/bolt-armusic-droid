package com.example.armusic

    import android.content.Context
    import androidx.preference.PreferenceManager

    class SettingsManager(context: Context) {
        private val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)

        var playbackSpeed: Float
            get() = sharedPrefs.getFloat("playback_speed", 1.0f)
            set(value) = sharedPrefs.edit().putFloat("playback_speed", value).apply()
    }
