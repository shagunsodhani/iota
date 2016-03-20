package com.shagunsodhani.iota.parser

import scala.xml.XML

object DataParser {

  def isValidRow(row: String): Boolean = {
    row.startsWith("<row Id=")
  }
  
  def parseUser(row: String): List[String] = {
    try {
      val xml = XML.loadString(row)
      val id = (xml \ "@Id").text.toString
      val reputation = (xml \ "@Reputation").text.toString
      val displayName = (xml \ "@DisplayName").text.toString
      val views = (xml \ "@Views").text.toString
      val upVotes = (xml \ "@UpVotes").text.toString
      val downvotes = (xml \ "@DownVotes").text.toString
      val accountId = (xml \ "@AccountId").text.toString
    List(id, reputation, displayName, views, upVotes, downvotes, accountId)
    } catch {
      case ex: Exception ⇒
        println(s"failed to parse line: $row")
        Nil
    }
  }
    
}