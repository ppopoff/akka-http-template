package com.doingfp.rest.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.doingfp.rest.routes
import scaldi.{Injectable, Injector}

/**
  * Created by i on 12/4/16.
  */
class Routes (implicit inj: Injector) extends Injectable {
  import routes._

  val helloRoutes   = inject [HelloRoutes]
  val swaggerRoutes = inject [SwaggerRoutes]

  def all: Route =
    helloRoutes() ~ swaggerRoutes()

}
