package com.jakewharton.crossword

private val ansiColorEscape = Regex("""\u001B\[\d+(;\d+)*m""")

fun CharSequence.visualIndex(index: Int): Int {
  var currentIndex = 0
  var remaining = index
  while (true) {
    val match = ansiColorEscape.find(this, startIndex = currentIndex) ?: break

    val jump = Character.codePointCount(this, currentIndex, match.range.first)
    if (jump > remaining) break

    remaining -= jump
    currentIndex = match.range.last + 1
  }

  while (remaining > 0) {
    val codePoint = Character.codePointAt(this, currentIndex)
    currentIndex += Character.charCount(codePoint)
    remaining--
  }

  return currentIndex
}

val CharSequence.visualCodePointCount: Int get() {
  // Fast path: no escapes.
  val firstEscape = indexOf('\u001B')
  if (firstEscape == -1) {
    return Character.codePointCount(this, 0, length)
  }

  var currentIndex = firstEscape
  var count = Character.codePointCount(this, 0, firstEscape)
  while (true) {
    val match = ansiColorEscape.find(this, startIndex = currentIndex) ?: break
    count += Character.codePointCount(this, currentIndex, match.range.first)
    currentIndex = match.range.last + 1
  }
  count += Character.codePointCount(this, currentIndex, length)
  return count
}
