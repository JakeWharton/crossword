# Crossword

A 2D canvas for rendering text, usually for console applications.

```kotlin
val canvas = TextCanvas(40, 8)
canvas.write(1, 20, "Such canvas")
canvas.write(6, 30, "Very 2D")
canvas.write(4, 7, "Much monospace")
println(canvas)
```
```
                                        
                    Such canvas         
                                        
                                        
       Much monospace                   
                                        
                              Very 2D   
                                        
```

Not very impressive. However, `TextCanvas` automatically handles multi-`char` codepoints and ANSI
control sequences.

For example, rendering a red `X` at (0,0) and a blue `O` at (0, 2) means writing 10 characters each: 
```kotlin
canvas.write(0, 0, "\u001B[31mX\u001B[0m")
canvas.write(0, 2, "\u001B[34mO\u001B[0m")
```
```
X O                                     
                    Such canvas         
                                        
                                        
       Much monospace                   
                                        
                              Very 2D   
                                        
```

_(Note: GitHub/markdown do not allow coloring text so use your imagination)_

If `TextCanvas` was a naive 2D `char[]` the `O` would have overwritten almost all of the `X`'s ANSI
escape sequence.


## Download

```groovy
repositories {
  mavenCentral()
}
dependencies {
  implementation 'com.jakewharton.crossword:crossword:0.1.2'
}
```

<details>
<summary>Snapshots of the development version are available in Sonatype's snapshots repository.</summary>
<p>

```groovy
repositories {
  maven {
    url 'https://oss.sonatype.org/content/repositories/snapshots/'
  }
}
dependencies {
  implementation 'com.jakewharton.crossword:crossword:0.2.0-SNAPSHOT'
}
```

</p>
</details>


# License

    Copyright 2020 Jake Wharton

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
