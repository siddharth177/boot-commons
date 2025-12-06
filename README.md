# boot-commons

A collection of common utility classes and components to accelerate Spring Boot application development.

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

---

## Table of Contents

- [boot-commons](#boot-commons)
  - [Table of Contents](#table-of-contents)
  - [Installation](#installation)
  - [Features](#features)
    - [Util](#util)
      - [`delay`](#delay)
      - [`nullOrEmpty`](#nullorempty)
    - [Swagger Configuration](#swagger-configuration)
  - [Contributing](#contributing)
  - [License](#license)

---

## Installation

To use `boot-commons` in your project, add the following dependency to your `pom.xml`:

```xml
<dependency>
  <groupId>com.helper</groupId>
  <artifactId>boot-commons</artifactId>
  <version>1.1.0</version>
</dependency>
```

---

## Features

This library provides a set of utility classes to simplify common development tasks.

### Util

The `Util` class contains generic helper methods that can be used across any project.

#### `delay`

Pauses the execution of the current thread for a specified number of seconds. This can be useful for testing, simulating network latency, or rate-limiting.

**Signature**
```java
public static void delay(int seconds)
```

**Example**
```java
import com.helper.bootcommons.utils.Util;

public class MyService {
    public void processWithDelay() {
        System.out.println("Starting process...");
        Util.delay(5); // Pauses for 5 seconds
        System.out.println("Process finished.");
    }
}
```

#### `nullOrEmpty`

A set of overloaded methods to safely check if a `String`, `List`, `Map`, or `Set` is `null` or empty. This helps prevent `NullPointerException` and reduces boilerplate code.

**Signatures**
```java
public static boolean nullOrEmpty(String string)
public static <T> boolean nullOrEmpty(List<T> list)
public static <K, V> boolean nullOrEmpty(Map<K, V> map)
public static <T> boolean nullOrEmpty(Set<T> set)
```

**Examples**

**String**
```java
import com.helper.bootcommons.utils.Util;

String myString = "";
if (Util.nullOrEmpty(myString)) {
    System.out.println("String is null or empty.");
}
```

**List**
```java
import com.helper.bootcommons.utils.Util;
import java.util.ArrayList;
import java.util.List;

List<String> myList = new ArrayList<>();
if (Util.nullOrEmpty(myList)) {
    System.out.println("List is null or empty.");
}
```

### Swagger Configuration

`boot-commons` provides a pre-configured `SwaggerConfig` that can be effortlessly plugged into any Spring Boot application to generate basic Swagger API documentation. This allows you to set up and customize Swagger with minimal effort, directly from your `application.properties` file.

To enable and configure Swagger, simply add the following properties to your `application.properties`:

```properties
# Swagger API Information
bootcommons.swagger.title=My Application API
bootcommons.swagger.description=A sample API for my awesome application
bootcommons.swagger.version=1.0.0

# Contact Information
bootcommons.swagger.contact.name=Your Name
bootcommons.swagger.contact.email=your.email@example.com

# API Groups (comma-separated)
bootcommons.swagger.groups=public,admin
```

The `SwaggerConfig` will automatically detect these properties and generate the corresponding OpenAPI documentation. The `bootcommons.swagger.groups` property allows you to define multiple API groups, which can be useful for organizing your endpoints.

---

## Contributing

Contributions are welcome! If you have a feature request, bug report, or want to contribute to the code, please feel free to open an issue or submit a pull request.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
