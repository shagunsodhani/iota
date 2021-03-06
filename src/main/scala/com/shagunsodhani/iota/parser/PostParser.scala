package com.shagunsodhani.iota.parser

import scala.xml.Elem
import scala.xml.XML

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.shagunsodhani.iota.schema.Answer
import com.shagunsodhani.iota.schema.Question

object PostParser extends DataParser {

  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def isQuestion(row: String): Boolean = {
    logger.debug("Checking if the input row is a question or not")
    val xml: Elem = XML.loadString(row)
    val postTypeId: String = (xml \ "@PostTypeId").text.toString
    postTypeId == "1"
  }

  //  @to-do: parse date as DateTime and not string
  def parseQuestion(row: String): Question = {
    logger.debug("Parsing row for question data")
    val xml: Elem = XML.loadString(row)
    val Id: Long = _parseAsLong(xml, "Id", -1)
    val AcceptedAnswerId: Long = _parseAsLong(xml, "AcceptedAnswerId", -1)
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
    val FavoriteCount: Long = _parseAsLong(xml, "FavoriteCount", 0)

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
    logger.debug("Checking if the input row is an answer or not")
    val xml: Elem = XML.loadString(row)
    val postTypeId: String = (xml \ "@PostTypeId").text.toString
    postTypeId == "2"
  }

  def parseAnswer(row: String): Answer = {
    logger.debug("Parsing row for answer data")
    val xml: Elem = XML.loadString(row)
    val Id: Long = _parseAsLong(xml, "Id", -1)
    val ParentId: Long = _parseAsLong(xml, "ParentId", -1)
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
      ParentId,
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
