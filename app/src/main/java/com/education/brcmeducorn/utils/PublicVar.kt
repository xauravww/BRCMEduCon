package com.education.brcmeducorn.utils

object PublicVar {
    // Private field
    private var position: Int = 0

    // Public getter
    fun getPosition(): Int {
        return position
    }

    // Public setter
    fun setPosition(newPosition: Int) {
        position = newPosition
    }
}
