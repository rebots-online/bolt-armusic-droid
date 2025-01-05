package com.example.armusic

    import android.content.Context
    import android.widget.Toast

    object ErrorHandler {
        fun showError(context: Context, message: String) {
            Toast.makeText(context, "Error: $message", Toast.LENGTH_LONG).show()
        }
    }
