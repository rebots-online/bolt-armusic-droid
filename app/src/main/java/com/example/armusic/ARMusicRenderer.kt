package com.example.armusic

import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ViewRenderable

class ARMusicRenderer {
    fun renderAnnotations(
        musicData: MusicSheetData,
        arFragment: ArFragment
    ) {
        musicData.notes.forEach { note ->
            val noteNode = Node().apply {
                setParent(arFragment.arSceneView.scene)
                localPosition = Vector3(
                    note.position.x.toFloat(),
                    note.position.y.toFloat(),
                    0f
                )
            }
            
            ViewRenderable.builder()
                .setView(arFragment.context, R.layout.note_annotation)
                .build()
                .thenAccept { renderable ->
                    noteNode.renderable = renderable
                }
        }
    }
}
