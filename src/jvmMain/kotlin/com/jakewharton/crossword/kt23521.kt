package com.jakewharton.crossword

@Suppress("NOTHING_TO_INLINE") // Alias to JVM function.
internal actual inline fun CharSequence.codePointCount(begin: Int, end: Int): Int {
  return Character.codePointCount(this, begin, end)
}
