package com.jakewharton.crossword

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TextCanvasTest {
  @Test fun zeroSized() {
    val output = TextCanvas(0, 0).toString()
    assertThat(output).isEqualTo("")
  }
}
