package com.shagunsodhani.iota.parser

import scala.xml.Elem
import scala.xml.XML

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.shagunsodhani.iota.schema.User

object UserParser extends DataParser {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  //  @to-do: parse date as DateTime and not string
  def parseUser(row: String): User = {
    logger.debug("Parsing row for user data")
    val xml: Elem = XML.loadString(row)

    val Id: Long = _parseAsLong(xml, "Id", -2) //-1 is reserved for admin user
    val Reputation: Long = _parseAsLong(xml, "Reputation", Long.MinValue)
    val CreationDate = (xml \ "@CreationDate").text.toString
    val DisplayName = (xml \ "@DisplayName").text.toString
    val EmailHash = (xml \ "@EmailHash").text.toString
    val LastAccessDate = (xml \ "@LastAccessDate").text.toString
    val WebsiteUrl = (xml \ "@WebsiteUrl").text.toString
    val Location = (xml \ "@Location").text.toString
    val Age = _parseAsLong(xml, "Age", -1)
    val AboutMe = (xml \ "@AboutMe").text.toString
    val Views = _parseAsLong(xml, "Views", -1)
    val UpVotes = _parseAsLong(xml, "UpVotes", -1)
    val DownVotes = _parseAsLong(xml, "DownVotes", -1)
    val AccountId = _parseAsLong(xml, "AccountId", -2) //-1 is reserved for admin user

    User(Id,
      Reputation,
      CreationDate,
      DisplayName,
      EmailHash,
      LastAccessDate,
      WebsiteUrl,
      Location,
      Age,
      AboutMe,
      Views,
      UpVotes,
      DownVotes,
      AccountId)
  }

}