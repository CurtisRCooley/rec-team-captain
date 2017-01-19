package controllers

import javax.inject._

import models.User
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class AdminController @Inject() extends Controller {

  def register() = Action {
    Ok(views.html.register("Please Register"))
  }
}

