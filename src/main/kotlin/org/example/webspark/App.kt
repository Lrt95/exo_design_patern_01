package org.example.webspark

import org.example.webspark.core.Conf
import org.example.webspark.core.Template
import org.example.webspark.middlewares.LoggerMiddleware
import spark.Request
import spark.Response
import spark.Route
import spark.Spark.*

fun main() {


    val homeSystem = HomeSystem.getInstance()


    homeSystem.addLight("Light 1")
    homeSystem.addLight("Light 2")

    val homeController = HomeController()
    val thingController = ThingController()
    for (light in homeSystem.lights) {
        println(light.getDescription())
    }

    initialize()

    get(
        "/",
        Route { req: Request, res: Response ->
            homeController.list(req, res)
        })

    get(
        "/things/:id",
        Route { req: Request, res: Response ->
            thingController.lightDetail(req, res)
        })
}

fun initialize() {
    Template.initialize()

    // Display exceptions in logs
    exception(
        Exception::class.java
    ) { e: Exception, _: Request?, _: Response? -> e.printStackTrace() }

    // Serve static files (img/css/js)
    staticFiles.externalLocation(Conf.STATIC_DIR.path)

    // Configure server port
    port(Conf.HTTP_PORT)
    val log = LoggerMiddleware()
    before(log::process)
}