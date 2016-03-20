package com.shagunsodhani.iota.parser

import scala.xml.Elem

trait DataParser {

  def isValidRow(row: String): Boolean = {
    row.startsWith("<row Id=")
  }

  protected def _parseAsLong(xml: Elem, attributeName: String, defaultValue: Long = 0) = {
    try {
      (xml \ "@".concat(attributeName)).text.toLong
    } catch {
      case ex: NumberFormatException =>
        println("failed to parse " + attributeName + " in line: "+xml.toString())
        println("===============================================================")
        defaultValue
    }
  }

}