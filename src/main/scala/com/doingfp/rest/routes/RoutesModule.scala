package com.doingfp.rest.routes

import scaldi.Module

class RoutesModule extends Module {
  // specific routes for each case
  bind [HelloRoutes] to new HelloRoutes()
  bind [SwaggerRoutes] to new SwaggerRoutes()

  // all routes
  bind [Routes] to new Routes()
}
