# Change Log

## [Unreleased]


## [0.1.1] - 2020-09-19

### Fixed

 * Set Java compatibility to 8. The bytecode targeted 8, but the Gradle module descriptor erroneously
   declared 15 (the deployment JDK) which prevented import into libraries with a lower JDK.


## [0.1.0] - 2020-09-18

Initial release


[Unreleased]: https://github.com/JakeWharton/crossword/compare/0.1.1...HEAD
[0.1.1]: https://github.com/JakeWharton/crossword/releases/tag/0.1.1
[0.1.0]: https://github.com/JakeWharton/crossword/releases/tag/0.1.0
