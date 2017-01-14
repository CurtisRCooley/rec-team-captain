package models


case class User(email: String)

object User {
  def save(user: User): Int = 99
  def create(user: User) = 99
}
