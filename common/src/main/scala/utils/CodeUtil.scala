package org.github.feather
package common.utils

import java.util.UUID

object CodeUtil {

  val chars: Array[String] = Array("a", "b", "c", "d", "e", "f",
    "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
    "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
    "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
    "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
    "W", "X", "Y", "Z")

  def simpleUUID(): String = {
    val str: String = UUID.randomUUID().toString.replace("-", "")
    val strings: Array[String] =
      Range(1, str.length / 4 + 1)
        .map((i: Int) => str.substring((i - 1) * 4, 4 * i))
        .toArray
    strings
      .map(Integer.parseInt(_, 16))
      .map(_ % 62)
      .map(chars(_))
      .mkString
  }

}
