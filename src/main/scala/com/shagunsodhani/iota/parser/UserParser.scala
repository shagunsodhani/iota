package com.shagunsodhani.iota.parser

import scala.xml.Elem
import scala.xml.XML

import com.shagunsodhani.iota.schema.User

object UserParser extends DataParser {

  //  @to-do: parse date as DateTime and not string
  def parseUser(row: String): User = {
    val xml: Elem = XML.loadString(row)

    val id: Long = _parseAsLong(xml, "Id")
    val reputation: Long = _parseAsLong(xml, "Reputation", -1)
    val creationDate = (xml \ "@CreationDate").text.toString
    val displayName = (xml \ "@DisplayName").text.toString
    val emailHash = (xml \ "@EmailHash").text.toString
    val lastAccessDate = (xml \ "@LastAccessDate").text.toString
    val websiteUrl = (xml \ "@WebsiteUrl").text.toString
    val location = (xml \ "@Location").text.toString
    val age = _parseAsLong(xml, "Age", -1)
    val aboutMe = (xml \ "@AboutMe").text.toString
    val views = _parseAsLong(xml, "Views", -1)
    val upVotes = _parseAsLong(xml, "UpVotes", -1)
    val downvotes = _parseAsLong(xml, "DownVotes", -1)
    val accountId = _parseAsLong(xml, "AccountId", 0)

    User(id, reputation, creationDate, displayName, emailHash, lastAccessDate,
      websiteUrl, location, age, aboutMe, views, upVotes, downvotes, accountId)
  }

}