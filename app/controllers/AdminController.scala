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
    Ok(views.html.register(userForm))
  }

  def saveContact = Action { implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.register.userForm(formWithErrors))
      },
      contact => {
        val id = User.save(contact)
        Redirect(routes.AdminController.showUser(id)).flashing("success" -> "Contact saved!")
      }
    )
  }

  val userPost = Action(parse.form(userForm)) { implicit request =>
    val userData = request.body
    val newUser = models.User(userData.email)
    val id = models.User.create(newUser)
    Redirect(routes.AdminController.showUser(id))
  }

  val userPostWithErrors = Action(parse.form(userForm, onErrors = (formWithErrors: Form[User]) => BadRequest(views.html.user(formWithErrors)))) { implicit request =>
    val userData = request.body
    val newUser = models.User(userData.email)
    val id = models.User.create(newUser)
    Redirect(routes.AdminController.home(id))
  }

  def showUser(user: User) = TODO

  val userForm: Form[User] = Form(
    mapping(
      "email" -> email
    )(User.apply)(User.unapply)
  )
}

