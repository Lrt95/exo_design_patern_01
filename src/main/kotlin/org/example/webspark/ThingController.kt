package org.example.webspark

import org.example.webspark.core.Template
import org.example.webspark.models.Light
import org.example.webspark.models.OnLightOnListener
import spark.Request
import spark.Response

class ThingController(){
    private val homeSystem = HomeSystem.getInstance()

    fun lightDetail(request: Request, response: Response): String {
        val id = request.params("id").toInt()
        val action = request.queryParamOrDefault("action", "")
        val light = homeSystem.things[id - 1] as Light
        if (action.equals("toggle")) {
            light.isLightOn = !light.isLightOn
        }
        return Template.render(
            "thing.html",
            hashMapOf(
                "id" to id,
                "light" to light
            ),
        )
    }

}