package com.jakewharton.crossword

import kotlin.test.Test
import kotlin.test.assertEquals

class TextCanvasTest {
  @Test fun zeroSized() {
    val output = TextCanvas(0, 0).toString()
    assertEquals("", output)
  }
}
