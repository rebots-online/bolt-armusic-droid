package com.example.armusic

    sealed class AppState {
        object Loading : AppState()
        data class Ready(val musicData: MusicSheetData) : AppState()
        data class Error(val message: String) : AppState()
    }
