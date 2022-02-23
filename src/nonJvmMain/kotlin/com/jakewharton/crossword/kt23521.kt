package com.jakewharton.crossword

internal actual fun CharSequence.codePointCount(begin: Int, end: Int): Int {
  var i = begin
  var count = 0
  while (i < end) {
    count++
    val code = this[i++].code
    if (code.isHighSurrogate()) {
      // TODO Should we validate next char is a low surrogate? What to do if not?
      i++
    }
  }
  return count
}

@Suppress("NOTHING_TO_INLINE")
private inline fun Int.isHighSurrogate(): Boolean = this in 0xD800..0xDBFF

internal actual fun CharSequence.codePointAt(index: Int): Int {
  var i = 0
  var skip = index
  var code: Int
  var isHighSurrogate: Boolean
  do {
    code = this[i++].code
    isHighSurrogate = code.isHighSurrogate()
    if (isHighSurrogate) {
      // TODO Should we validate next char is a low surrogate? What to do if not?
      i++
    }
  } while (skip-- != 0)

  // Note: 'i' is already pointing at next position from loop above.
  if (isHighSurrogate && i < length) {
    code = (code shl 16) or this[i].code
  }
  return code
}

internal actual fun codePointCharCount(codePoint: Int): Int {
  // Check if any upper bits are set indicating this won't fit in a single UTF-16 char.
  // -65536 is 0xFFFF0000 but in decimal because of Kotlin's goofy-ass signed literals.
  return if ((codePoint and -65536) == 0) 1 else 2
}
