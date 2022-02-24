package com.jakewharton.crossword

import kotlin.DeprecationLevel.ERROR

fun CharSequence.visualIndex(index: Int): Int {
  var remaining = index
  forEachVisualCharacter {
    if (remaining == 0) {
      return it
    }
    remaining--
  }
  if (remaining == 0) {
    return length
  }
  throw IndexOutOfBoundsException()
}

@Suppress("unused") // Delete after 0.2.0 is released.
@Deprecated("Renamed", ReplaceWith("this.visualWidth"), ERROR)
val CharSequence.visualCodePointCount: Int get() = visualWidth

val CharSequence.visualWidth: Int get() {
  var count = 0
  forEachVisualCharacter {
    count++
  }
  return count
}

private inline fun CharSequence.forEachVisualCharacter(block: (index: Int) -> Unit) {
  // TODO Determine if it's better and/or faster to be comparing as chars or codes.

  var i = 0
  val length = length
  outer@ while (i < length) {
    val code = this[i].code

    // Skip ANSI control sequence – they do not affect rendering position.
    if (code == 0x1B) {
      var state = 0
      for (peek in i + 1 until length) {
        val peeked = this[peek]
        when (state) {
          0 -> {
            if (peeked != '[') {
              break
            }
            state = 1
          }
          1 -> {
            if (peeked !in '0'..'9') {
              break
            }
            state = 2
          }
          2 -> {
            if (peeked == ';') {
              state = 1
            } else if (peeked == 'm') {
              // Sequence is complete! Bump pointer and start over in outer loop in case there are
              // successive sequences.
              i = peek + 1
              continue@outer
            } else if (peeked !in '0'..'9') {
              break
            }
          }
        }
      }
    }

    block(i)

    i++

    // If code is a high surrogate followed by a low surrogate they will combine when rendering.
    if (code in 0xD800..0xDBFF && i < length && this[i].code in 0xDC00..0xDFFF) {
      i++
    }
  }
}
