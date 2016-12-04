package com.doingfp.rest.controllers

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import scaldi.Injector

/**
  * Created by i on 12/4/16.
  */
class HelloController(implicit inj: Injector) {
  val html = "<h1>Say hello to akka-http</h1>"
  val response = HttpEntity(ContentTypes.`text/html(UTF-8)`, html)
}
