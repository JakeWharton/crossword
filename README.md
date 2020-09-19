# Crossword

A 2D canvas for rendering text, usually for console applications.

```kotlin
val canvas = TextCanvas.sized(40, 8)
canvas.write(1, 20, "Such canvas")
canvas.write(6, 30, "Very 2D")
canvas.write(4, 7, "Much monnospace")
println(canvas)
```
```
                                        
                    Such canvas         
                                        
                                        
       Much monnospace                  
                                        
                              Very 2D   
                                        
```


## Download

Soon.


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
