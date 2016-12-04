package com.doingfp.rest.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import scaldi.{Injectable, Injector}

class SwaggerRoutes(implicit inj: Injector) extends Injectable {
  def apply(): Route = path("swagger") { get {
    complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "swagger template"))
  }}
}
