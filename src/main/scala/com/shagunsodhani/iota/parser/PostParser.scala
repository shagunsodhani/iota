package com.shagunsodhani.iota.parser

import scala.xml.Elem
import scala.xml.XML
import com.shagunsodhani.iota.schema.Question
import com.shagunsodhani.iota.schema.Answer

object PostParser extends DataParser {

  def isQuestion(row: String): Boolean = {
    val xml: Elem = XML.loadString(row)
    val postTypeId: String = (xml \ "@PostTypeId").text.toString
    postTypeId == "1"
  }

  //  @to-do: parse date as DateTime and not string
  def parseQuestion(row: String): Question = {
    val xml: Elem = XML.loadString(row)
    val Id: Long = _parseAsLong(xml, "Id", -1)
    val AcceptedAnswerId: Long = _parseAsLong(xml, "AcceptedAnswer", -1)
    val CreationDate: String = (xml \ "@CreationDate").text.toString
    val Score: Long = _parseAsLong(xml, "Score", Long.MinValue)
    val ViewCount: Long = _parseAsLong(xml, "ViewCount", Long.MinValue)
    val Body: String = (xml \ "@Body").text.toString
    val OwnerUserId: Long = _parseAsLong(xml, "OwnerUserId", -2)
    val LastEditorUserId: Long = _parseAsLong(xml, "LastEditorUserId", -2)
    val LastEditorDisplayName: String = (xml \ "@LastEditorDisplayName").text.toString
    val LastEditDate: String = (xml \ "@LastEditDisplayName").text.toString
    val LastActivityDate: String = (xml \ "@LastActivityDate").text.toString
    val CommunityOwnedDate: String = (xml \ "@CommunityOwnedDate").text.toString
    val ClosedDate: String = (xml \ "@ClosedDate").text.toString
    val Title: String = (xml \ "@Title").text.toString
    val Tags: String = (xml \ "@Tags").text.toString
    val AnswerCount: Long = _parseAsLong(xml, "AnswerCount", -1)
    val CommentCount: Long = _parseAsLong(xml, "CommentCount", -1)
    val FavoriteCount: Long = _parseAsLong(xml, "FavoriteCount", -1)

    Question(Id,
      AcceptedAnswerId,
      CreationDate,
      Score,
      ViewCount,
      Body,
      OwnerUserId,
      LastEditorUserId,
      LastEditorDisplayName,
      LastEditDate,
      LastActivityDate,
      CommunityOwnedDate,
      ClosedDate,
      Title,
      Tags,
      AnswerCount,
      CommentCount,
      FavoriteCount)
  }

  def isAnswer(row: String): Boolean = {
    val xml: Elem = XML.loadString(row)
    val postTypeId: String = (xml \ "@PostTypeId").text.toString
    postTypeId == "2"
  }
  
  def parseAnswer(row: String): Answer = {
    val xml: Elem = XML.loadString(row)
    val Id: Long = _parseAsLong(xml, "Id", -1)
    val ParentID: Long = _parseAsLong(xml, "ParentID", -1)
    val CreationDate: String = (xml \ "@CreationDate").text.toString
    val Score: Long = _parseAsLong(xml, "Score", Long.MinValue)
    val ViewCount: Long = _parseAsLong(xml, "ViewCount", Long.MinValue)
    val Body: String = (xml \ "@Body").text.toString
    val OwnerUserId: Long = _parseAsLong(xml, "OwnerUserId", -2)
    val LastEditorUserId: Long = _parseAsLong(xml, "LastEditorUserId", -2)
    val LastEditorDisplayName: String = (xml \ "@LastEditorDisplayName").text.toString
    val LastEditDate: String = (xml \ "@LastEditDisplayName").text.toString
    val LastActivityDate: String = (xml \ "@LastActivityDate").text.toString
    val CommunityOwnedDate: String = (xml \ "@CommunityOwnedDate").text.toString
    val ClosedDate: String = (xml \ "@ClosedDate").text.toString
    val Title: String = (xml \ "@Title").text.toString
    val Tags: String = (xml \ "@Tags").text.toString
    val AnswerCount: Long = _parseAsLong(xml, "AnswerCount", -1)
    val CommentCount: Long = _parseAsLong(xml, "CommentCount", -1)
    val FavoriteCount: Long = _parseAsLong(xml, "FavoriteCount", -1)

    Answer(Id,
      ParentID,
      CreationDate,
      Score,
      ViewCount,
      Body,
      OwnerUserId,
      LastEditorUserId,
      LastEditorDisplayName,
      LastEditDate,
      LastActivityDate,
      CommunityOwnedDate,
      ClosedDate,
      Title,
      Tags,
      AnswerCount,
      CommentCount,
      FavoriteCount)
  }
}