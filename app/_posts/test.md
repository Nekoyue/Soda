---
title: Hello, World?
description: The ultimate test article I scrambled up on a Sunday afternoon
author: Kagurazaka Tsuki
date: 2022-12-04
---

This is a markdown test page.

_Italic_

**Bold**

**_Italic Bold_**

~~Strikethrough~~

[Hyperlink](/posts/test)

Inline `code`

也试一下中文吧？

にほんご本当上手！

门门门门

門門門門

# H1

Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna
aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis
aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint
occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

## H2

### H3

```
Code block
```

```python
# Python code
class A:
    def __init__(self, args):
        self.args = args

    def main(self, args):
        a = (1 + 1) * 31
        b = True
        print(f"Output {args}: {a}, {not b}\n".strip())
```

```kotlin
// Kotlin code
class A<T>(var args: List<T>) {
    fun main(args: List<T> = this.args): Unit {
        val a: Int? = null ?: (1 + 1) * 31
        val b: Boolean = true
        println(message = "Output $args: $a, ${!b}\n".trim())
    }
}
```

```rust
// Rust code
pub struct A<T> {
    args: Vec<T>,
}

impl A<i32> {
    fn main(args: Vec<i32>) {
        let a: i32 = Some((1 + 1) * 32).unwrap();
        let mut b: bool = true;
        b = !b;
        println!("{}", format!("Output {args:?}: {a}, {b}\n"));
    }
}
```

---
^^ Divider

> Blockquotes

<tabs group="build-script">

<tab title="Kotlin" group-key="kotlin">

```kotlin
plugins {
    kotlin("multiplatform") version "%kotlinVersion%"
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```
plugins {
    id 'org.jetbrains.kotlin.multiplatform' version '%kotlinVersion%'
}
```

</tab>
</tabs>


<script>alert("XSS injection test")</script>

> <script>alert("XSS injection test 2")</script>
