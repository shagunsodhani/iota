package com.shagunsodhani.iota.parser

import scala.xml.Elem

import org.slf4j.Logger
import org.slf4j.LoggerFactory

trait DataParser {

  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def isValidRow(row: String): Boolean = {

    row.startsWith("<row Id=")
  }

  protected def _parseAsLong(xml: Elem, attributeName: String, defaultValue: Long = 0) = {
    try {
      (xml \ "@".concat(attributeName)).text.toLong
    } catch {
      case ex: NumberFormatException =>
        logger.warn("Failed to parse " + attributeName + " in line: " + xml.toString())
        defaultValue
    }
  }
}