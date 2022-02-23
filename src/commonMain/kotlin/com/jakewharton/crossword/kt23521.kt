package com.jakewharton.crossword

// TODO https://youtrack.jetbrains.com/issue/KT-23251

internal expect fun CharSequence.codePointCount(begin: Int, end: Int): Int
