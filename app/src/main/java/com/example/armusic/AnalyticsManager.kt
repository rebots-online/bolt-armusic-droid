package com.example.armusic

    import com.google.firebase.analytics.FirebaseAnalytics
    import android.content.Context

    class AnalyticsManager(context: Context) {
        private val firebaseAnalytics = FirebaseAnalytics.getInstance(context)

        fun logEvent(eventName: String, params: Map<String, String>? = null) {
            val bundle = params?.let { 
                Bundle().apply {
                    it.forEach { (key, value) -> putString(key, value) }
                }
            }
            firebaseAnalytics.logEvent(eventName, bundle)
        }
    }
