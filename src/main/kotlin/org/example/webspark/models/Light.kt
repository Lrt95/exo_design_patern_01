package org.example.webspark.models

interface OnLightOnListener {
    fun onLightOn(light: Light)
}

class Light: Thing() {
    var isLightOn: Boolean = false
        set(value) {
            lightOn()
            field = value
        }

    var lightOnListener: OnLightOnListener? = null;

    override fun getTypeName(): String = "Light"
    override fun getDescription(): String = "Light=$name, reachable=$state, is on=$isLightOn"

    fun lightOn()  {
        lightOnListener?.onLightOn(this)
    }
}