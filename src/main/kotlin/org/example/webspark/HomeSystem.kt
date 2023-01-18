package org.example.webspark

import org.example.webspark.models.Light
import org.example.webspark.models.OnLightOnListener
import org.example.webspark.models.Thing

class HomeSystem private constructor() : OnLightOnListener{

    var things = mutableListOf<Thing>()
    private var messages = mutableListOf<String>()

    companion object {

        @Volatile
        private lateinit var instance: HomeSystem

        fun getInstance(): HomeSystem {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = HomeSystem()
                }
                return instance
            }
        }
    }

    fun addLight(name: String) {
        val light = Light();
        light.name = name
        light.lightOnListener = this
        things.add(light)
    }

    val lights: List<Light>
        get() = things.filterIsInstance<Light>()

    override fun onLightOn(light: Light) {
        messages.add(light.name + " updated light on=" + light.isLightOn)
        println(messages.last())
    }

}