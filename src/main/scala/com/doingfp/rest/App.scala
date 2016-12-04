package com.doingfp.rest

import scala.concurrent.ExecutionContextExecutor
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.doingfp.rest.routes.Routes
import scaldi.akka.AkkaInjectable
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import modules._


object App extends AkkaInjectable with LazyLogging {
  // application modules
  implicit val applicationModule = modules.all

  implicit val system       = inject [ActorSystem]
  implicit val materializer = inject [ActorMaterializer]
  implicit val execContext  = inject [ExecutionContextExecutor]

  val config = inject [Config]
  val routes = inject [Routes]

  def main(args: Array[String]): Unit = {
    val host = config getString "server.host"
    val port = config getInt "server.port"

    val serverBinding =
      Http().bindAndHandle(routes.all, host, port)

    logger.info(s"Starting the server on $host:$port")

    serverBinding.failed map { exception =>
      logger.error(s"Server binding failed with ${exception.getMessage}")
      shutdown()
    }
  }

  def shutdown(): Unit = {
    applicationModule.destroy()
    logger.info("Server has stopped...")
  }
}
