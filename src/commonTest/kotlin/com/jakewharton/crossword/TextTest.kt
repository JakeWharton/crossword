package com.jakewharton.crossword

import kotlin.test.Test
import kotlin.test.assertEquals

class TextTest {
  @Test fun visualIndex() {
    assertEquals(0, "AAAAA".visualIndex(0))
    assertEquals(1, "AAAAA".visualIndex(1))
    assertEquals(2, "AAAAA".visualIndex(2))
    assertEquals(3, "AAAAA".visualIndex(3))
    assertEquals(4, "AAAAA".visualIndex(4))

    assertEquals(1, "\u0031a".visualIndex(1))
    assertEquals(1, "\u00A3a".visualIndex(1))
    assertEquals(1, "\u20ACa".visualIndex(1))
    assertEquals(1, "\u5317a".visualIndex(1))

    // High, low, ASCII
    assertEquals(2, "\uD83D\uDE03a".visualIndex(1))

    // High, high, low, low --> ?😃?
    assertEquals(1, "\uD83D\uD83D\uDE03\uDE03".visualIndex(1))
    assertEquals(3, "\uD83D\uD83D\uDE03\uDE03".visualIndex(2))
    // Low, low, high, high --> ????
    assertEquals(1, "\uDE03\uDE03\uD83D\uD83D".visualIndex(1))
    assertEquals(2, "\uDE03\uDE03\uD83D\uD83D".visualIndex(2))
    assertEquals(3, "\uDE03\uDE03\uD83D\uD83D".visualIndex(3))
  }

  @Test fun visualIndexJumpsAnsiEscapes() {
    val singleAnsiEscape = "AAA\u001B[31;1;4mAA"
    assertEquals(0, singleAnsiEscape.visualIndex(0))
    assertEquals(1, singleAnsiEscape.visualIndex(1))
    assertEquals(2, singleAnsiEscape.visualIndex(2))
    assertEquals(12, singleAnsiEscape.visualIndex(3))
    assertEquals(13, singleAnsiEscape.visualIndex(4))

    val dualAdjacentAnsiEscapes = "AAA\u001B[31;1;4m\u001B[0mAA"
    assertEquals(0, dualAdjacentAnsiEscapes.visualIndex(0))
    assertEquals(1, dualAdjacentAnsiEscapes.visualIndex(1))
    assertEquals(2, dualAdjacentAnsiEscapes.visualIndex(2))
    assertEquals(16, dualAdjacentAnsiEscapes.visualIndex(3))
    assertEquals(17, dualAdjacentAnsiEscapes.visualIndex(4))

    val dualAnsiEscapes = "AAA\u001B[31;1;4mAA\u001B[31;1;4mAA"
    assertEquals(0, dualAnsiEscapes.visualIndex(0))
    assertEquals(1, dualAnsiEscapes.visualIndex(1))
    assertEquals(2, dualAnsiEscapes.visualIndex(2))
    assertEquals(12, dualAnsiEscapes.visualIndex(3))
    assertEquals(13, dualAnsiEscapes.visualIndex(4))
  }

  @Test fun visualCodePointCountAscii() {
    assertEquals(0, "".visualCodePointCount)
    assertEquals(1, "A".visualCodePointCount)
    assertEquals(2, "AA".visualCodePointCount)
    assertEquals(3, "AAA".visualCodePointCount)

    assertEquals(2, "\u0031a".visualCodePointCount)
    assertEquals(2, "\u00A3a".visualCodePointCount)
    assertEquals(2, "\u20ACa".visualCodePointCount)
    assertEquals(2, "\u5317a".visualCodePointCount)

    // High, low, ASCII
    assertEquals(2, "\uD83D\uDE03a".visualCodePointCount)

    // High, high, low, low --> ?😃?
    assertEquals(3, "\uD83D\uD83D\uDE03\uDE03".visualCodePointCount)
    // Low, low, high, high --> ????
    assertEquals(4, "\uDE03\uDE03\uD83D\uD83D".visualCodePointCount)
  }

  @Test fun visualCodePointCountAnsiEscapes() {
    assertEquals(1, "\u001B[31;1;4mA\u001B[0m".visualCodePointCount)
    assertEquals(3, "A\u001B[31;1;4mA\u001B[0mA".visualCodePointCount)

    assertEquals(3, "\u001B[31;1;4mA\u001B[0m\u001B[31;1;4mA\u001B[0mA".visualCodePointCount)
  }
}
