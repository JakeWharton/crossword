package com.jakewharton.crossword

import kotlin.DeprecationLevel.ERROR

private val ansiColorEscape = Regex("""\u001B\[\d+(;\d+)*m""")

fun CharSequence.visualIndex(index: Int): Int {
  var currentIndex = 0
  var remaining = index
  while (true) {
    val match = ansiColorEscape.find(this, startIndex = currentIndex) ?: break

    val jump = codePointCount(currentIndex, match.range.first)
    if (jump > remaining) break

    remaining -= jump
    currentIndex = match.range.last + 1
  }

  while (remaining > 0) {
    val codePoint = codePointAt(currentIndex)
    currentIndex += codePointCharCount(codePoint)
    remaining--
  }

  return currentIndex
}

@Deprecated("Renamed", ReplaceWith("this.visualWidth"), ERROR)
val CharSequence.visualCodePointCount: Int get() = visualWidth

val CharSequence.visualWidth: Int get() {
  // Fast path: no escapes.
  val firstEscape = indexOf('\u001B')
  if (firstEscape == -1) {
    return codePointCount(0, length)
  }

  var currentIndex = firstEscape
  var count = codePointCount(0, firstEscape)
  while (true) {
    val match = ansiColorEscape.find(this, startIndex = currentIndex) ?: break
    count += codePointCount(currentIndex, match.range.first)
    currentIndex = match.range.last + 1
  }
  count += codePointCount(currentIndex, length)
  return count
}
