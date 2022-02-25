package com.jakewharton.crossword

import kotlin.test.Test
import kotlin.test.assertEquals

class TextCanvasTest {
  @Test fun zeroSized() {
    val canvas = TextCanvas(0, 0)
    assertEquals("", canvas.toString())
  }

  @Test fun fourCorners() {
    val canvas = TextCanvas(10, 4).apply {
      write(0, 0, 'a')
      write(0, 9, 'b')
      write(3, 0, 'c')
      write(3, 9, 'd')
    }
    assertEquals("""
      |a        b
      |          
      |          
      |c        d
      """.trimMargin(), canvas.toString())
  }

  @Test fun ansiControlSequences() {
    val canvas = TextCanvas(5, 2).apply {
      write(0, 0, "\u001B[31mX\u001B[0m")
      write(0, 2, "\u001B[34mO\u001B[0m")
      write(0, 4, 'X')
      write(1, 0, 'Y')
      write(1, 2, 'Z')
      write(1, 4, 'Y')
    }
    assertEquals("""
      |$esc[31mX$esc[0m $esc[34mO$esc[0m X
      |Y Z Y
      """.trimMargin(), canvas.toString())
  }

  private val esc = "\u001B"
}
