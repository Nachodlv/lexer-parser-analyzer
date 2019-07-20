# Lexer, parser and interpreter
A lexer, parser and interpreter for typescript.

## Features

### Variable declaration

When a variable is declared it should be always be assigned.  
Supported types:
- number
- string

``` typescript
let foo: number = 0;
let foo2: string = "Hello World";
```

### Variable assignation

``` typescript
let foo: number = 0;
foo = 3;
```

### Printing
``` typescript
print("Hello world");
```

### Arithmetics

Supported operations:
- Sum (+)
- Subtraction (-)
- Division (/)
- Multiplication (*)

Parenthesis are not supported

Operating between numbers
``` typescript
let foo: number = 3;
let result: number = foo * 2 + 4; // 10
```

Operating between strings
``` typescript
let hello: string = "Hello";
let world: string = "World";
let result: string = hello + " " + world; // "Hello World"
```

Operating between numbers and strings
``` typescript
let hello: string = "Hello World!";
let foo: number = 4;
let result: string = hello + foo; // "Hello World!4"
```
