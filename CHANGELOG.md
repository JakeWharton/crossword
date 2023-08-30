# Change Log

## [Unreleased]


## [0.3.0] - 2023-08-31

### Added

 * New Kotlin targets:
   * `androidNativeArm32`
   * `androidNativeArm64`
   * `androidNativeX86`
   * `androidNativeX64`
   * `linuxArm64`
   * `wasm`
   * `watchosDeviceArm64`

### Changed

 * Publish using a newer version of Kotlin to produce non-legacy artifacts.

### Removed

 * Deprecated `CharSequence.visualCodePointCount` extension removed. Use `CharSequence.visualWidth`.


## [0.2.0] - 2022-02-25

### Added

 * Library is now fully multiplatform supporting JS and native targets in addition to JVM/Android.

### Fixed

 * Properly measure combining diacritics as part of the previous glyph.
 * `visualCodePointCount` renamed to `visualWidth` since it does not simply count code points.



## [0.1.2] - 2020-10-10

### Fixed

 * Support a zero-sized `TextCanvas`.


## [0.1.1] - 2020-09-19

### Fixed

 * Set Java compatibility to 8. The bytecode targeted 8, but the Gradle module descriptor erroneously
   declared 15 (the deployment JDK) which prevented import into libraries with a lower JDK.


## [0.1.0] - 2020-09-18

Initial release


[Unreleased]: https://github.com/JakeWharton/crossword/compare/0.3.0...HEAD
[0.3.0]: https://github.com/JakeWharton/crossword/releases/tag/0.3.0
[0.2.0]: https://github.com/JakeWharton/crossword/releases/tag/0.2.0
[0.1.2]: https://github.com/JakeWharton/crossword/releases/tag/0.1.2
[0.1.1]: https://github.com/JakeWharton/crossword/releases/tag/0.1.1
[0.1.0]: https://github.com/JakeWharton/crossword/releases/tag/0.1.0
