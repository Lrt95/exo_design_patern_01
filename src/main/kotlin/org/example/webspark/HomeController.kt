package org.example.webspark

import org.example.webspark.core.Template
import spark.Request
import spark.Response

class HomeController() {

    private val homeSystem = HomeSystem.getInstance()

    fun list(request: Request, response: Response): String = Template.render(
        "home.html",
        hashMapOf(
            "things" to homeSystem.things
        ),
    )
}