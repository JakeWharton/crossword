package com.jakewharton.crossword

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic

private class TextSurface(
  override val width: Int,
  override val height: Int,
) : TextCanvas {
  private val rowBuilders = Array(height) {
    StringBuilder(width).apply {
      repeat(width) {
        append(' ')
      }
    }
  }

  override fun write(row: Int, column: Int, char: Char) {
    val rowBuilder = rowBuilders[row]
    val writeIndex = rowBuilder.visualIndex(column)
    rowBuilder[writeIndex] = char
  }

  override fun write(row: Int, column: Int, string: String) {
    string.split('\n').forEachIndexed { lineIndex, line ->
      val rowBuilder = rowBuilders[row + lineIndex]
      val writeStartIndex = rowBuilder.visualIndex(column)
      val writeEndIndex = rowBuilder.visualIndex(column + line.visualWidth)

      rowBuilder.setRange(writeStartIndex, writeEndIndex, line)
    }
  }

  override fun toString(): String {
    if (height == 0) {
      return ""
    }
    val rowCharacterCount = rowBuilders.sumOf { it.length }
    val newLineCount = height - 1
    return buildString(rowCharacterCount + newLineCount) {
      rowBuilders.forEachIndexed { index, rowBuilder ->
        if (index > 0) {
          append('\n')
        }
        append(rowBuilder)
      }
    }
  }
}

interface TextCanvas {
  val width: Int
  val height: Int

  fun write(row: Int, column: Int, char: Char)
  fun write(row: Int, column: Int, string: String)

  fun clip(left: Int, top: Int, right: Int, bottom: Int): TextCanvas {
    return ClippedTextCanvas(this, left, right, top, bottom)
  }

  override fun toString(): String

  companion object {
    @JvmStatic
    @JvmName("ofSize")
    operator fun invoke(width: Int, height: Int): TextCanvas {
      return TextSurface(width, height)
    }
  }
}

private class ClippedTextCanvas(
  private val canvas: TextCanvas,
  private val left: Int,
  right: Int,
  private val top: Int,
  bottom: Int,
) : TextCanvas {
  override val width = right - left
  override val height = bottom - top

  override fun write(row: Int, column: Int, char: Char) {
    canvas.write(top + row, left + column, char)
  }

  override fun write(row: Int, column: Int, string: String) {
    canvas.write(top + row, left + column, string)
  }

  override fun toString(): String {
    throw UnsupportedOperationException("Rendering a clipped canvas is not supported")
  }
}
