package com.jakewharton.crossword

@Suppress("NOTHING_TO_INLINE") // Alias to JVM function.
internal actual inline fun CharSequence.codePointCount(begin: Int, end: Int): Int {
  return Character.codePointCount(this, begin, end)
}

@Suppress("NOTHING_TO_INLINE") // Alias to JVM function.
internal actual inline fun CharSequence.codePointAt(index: Int): Int {
  return Character.codePointAt(this, index)
}

@Suppress("NOTHING_TO_INLINE") // Alias to JVM function.
internal actual inline fun codePointCharCount(codePoint: Int): Int {
  return Character.charCount(codePoint)
}
