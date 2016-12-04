package com.doingfp.rest

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.doingfp.rest.controllers.ControllersModule
import com.doingfp.rest.routes.RoutesModule
import com.typesafe.config.{Config, ConfigFactory}
import scaldi.Module

import scala.concurrent.ExecutionContextExecutor

/**
  * Created by i on 12/4/16.
  */
package object modules {

  val configModule = new Module {
    bind [Config] to ConfigFactory.load()
  }

  val akkaModule = new Module {
    implicit val actorSystem = ActorSystem()
    implicit val materializer = ActorMaterializer()

    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = actorSystem.dispatcher

    bind [ActorSystem] to actorSystem destroyWith (system => system.terminate())
    bind [ActorMaterializer] to materializer
    bind [ExecutionContextExecutor] to executionContext
  }

  // Module that contains all bindings for the application
  val all = akkaModule :: configModule :: new RoutesModule :: new ControllersModule


  // Please note, that actors must always be bound with `toProvider`!
  // This will make sure, that each time Akka asks Scaldi for some particular
  // Actor, it will always get the new instance of it.
  val topLevelActorsModule = new Module {
    // todo: top level actors should be added here
  }
}
