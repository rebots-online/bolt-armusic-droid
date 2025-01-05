package com.example.armusic

import android.media.midi.MidiManager
import android.media.midi.MidiOutputPort
import android.media.midi.MidiReceiver

class MusicPlayer(private val midiManager: MidiManager) {
    private var outputPort: MidiOutputPort? = null
    
    fun playNote(note: String) {
        val midiNote = convertNoteToMidi(note)
        outputPort?.send(midiNote, 0, midiNote.size)
    }
    
    private fun convertNoteToMidi(note: String): ByteArray {
        // Convert note name to MIDI bytes
        return byteArrayOf(
            (0x90).toByte(),  // Note on
            noteToMidiValue(note).toByte(),
            0x7F.toByte()     // Velocity
        )
    }
    
    private fun noteToMidiValue(note: String): Int {
        // Convert note name to MIDI value
        return when (note) {
            "C4" -> 60
            "D4" -> 62
            "E4" -> 64
            // Add more notes
            else -> 60
        }
    }
}
