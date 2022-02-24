package com.jakewharton.crossword

import kotlin.DeprecationLevel.ERROR

private val ansiColorEscape = Regex("""\u001B\[\d+(;\d+)*m""")

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
  var index = 0

  // These values will force a code path that searches for the first real match below.
  var nextMatchStart = 0
  var nextMatchEnd = -1

  val length = length
  while (index < length) {
    if (index == nextMatchStart) {
      // Jump over ANSI control sequence.
      index = nextMatchEnd + 1

      // Find the next ANSI control sequence, if any.
      val match = ansiColorEscape.find(this, index)
      if (match != null) {
        nextMatchStart = match.range.first
        nextMatchEnd = match.range.last
      } else {
        // No future matches. Ensure we never take this conditional again.
        nextMatchStart = length
      }

      continue
    }

    block(index)

    val code = this[index].code
    index++
    if (code.isHighSurrogate() && index < length && this[index].code.isLowSurrogate()) {
      index++
    }
  }
}

@Suppress("NOTHING_TO_INLINE")
private inline fun Int.isLowSurrogate(): Boolean = this in 0xDC00..0xDFFF
@Suppress("NOTHING_TO_INLINE")
private inline fun Int.isHighSurrogate(): Boolean = this in 0xD800..0xDBFF
