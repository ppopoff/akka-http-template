package com.doingfp.rest.controllers

import scaldi.Module

class ControllersModule extends Module {
  bind [HelloController] to new HelloController
  bind [IndexController] to new IndexController
}
