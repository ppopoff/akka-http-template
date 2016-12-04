package com.doingfp.rest.routes

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import com.doingfp.rest.controllers.{HelloController, IndexController}
import scaldi.{Injectable, Injector}

/**
  * Created by i on 12/4/16.
  */
class HelloRoutes(implicit inj: Injector) extends Injectable {

  val indexController = inject [IndexController]
  val helloController = inject [HelloController]

  def apply(): Route =
    path("") { get {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, indexController.html))
    }} ~
    path("hello") { get {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, helloController.html))
    }}
}
