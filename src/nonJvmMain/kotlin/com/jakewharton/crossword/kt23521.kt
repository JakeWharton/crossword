package com.jakewharton.crossword

internal actual fun CharSequence.codePointCount(begin: Int, end: Int): Int {
  var i = begin
  var count = 0
  while (i < end) {
    count++
    val code = this[i++].code
    if (code.isHighSurrogate() && i < end && this[i].code.isLowSurrogate()) {
      i++
    }
  }
  return count
}
