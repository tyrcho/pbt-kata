package properties

import rapture._
import core._, json._
import jsonBackends.jawn._
import formatters.humanReadable._

object JsonSerialization {
  def toString(p: Person) = Json(p).toString
  def fromString(s: String) = Json.parse(s).as[Person]
}

case class Person(name: String, address: Address)
case class Address(street: String, town: String, zip: Int)