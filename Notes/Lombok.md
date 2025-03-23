### **Project Lombok Overview**

**Project Lombok** is a Java library designed to **reduce boilerplate code**. It leverages annotations to automatically generate common methods like `getters`, `setters`, `constructors`, `equals()`, `hashCode()`, and `toString()`, thereby simplifying development and enhancing code readability.

---

### **How Lombok Works**

Lombok integrates with the Java build process, processing annotations at **compile-time**. It injects generated methods directly into the bytecode, meaning they don’t appear in the source code but exist in the compiled `.class` files.

---

### **Key Annotations**

| Annotation                     | Purpose                                                                 |
|---------------------------------|-------------------------------------------------------------------------|
| `@Getter` / `@Setter`           | Generates getter and setter methods for fields.                         |
| `@NoArgsConstructor`            | Creates a no-args constructor.                                          |
| `@AllArgsConstructor`           | Generates a constructor with all fields as parameters.                  |
| `@RequiredArgsConstructor`      | Constructor for `final` or `@NonNull` fields.                           |
| `@Data`                         | Combines `@Getter`, `@Setter`, `@EqualsAndHashCode`, `@ToString`, and `@RequiredArgsConstructor`. |
| `@Value`                        | Similar to `@Data` but creates an immutable class (fields are `final`, no setters). |
| `@Builder`                      | Implements the builder pattern for easy object creation.                |
| `@EqualsAndHashCode`            | Generates `equals()` and `hashCode()` methods.                          |
| `@ToString`                     | Generates a `toString()` method.                                        |
| `@Log4j`, `@Slf4j`              | Automatically creates logger instances (e.g., `log.info()`).            |

---

### **Example**

#### Without Lombok:
```java
public class Person {
    private String name;
    private int age;

    // Constructor
    public Person(String name, int age) { 
        this.name = name; 
        this.age = age; 
    }

    // Getters and Setters
    // equals(), hashCode(), toString()...
}
```

#### With Lombok:
```java
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Person {
    private String name;
    private int age;
}
```

---

### **Installation**

To use Lombok, add it as a dependency in your **Maven** or **Gradle** project and install the Lombok plugin in your IDE (IntelliJ, Eclipse, etc.).

**Maven:**
```xml
<dependency>
   <groupId>org.projectlombok</groupId>
   <artifactId>lombok</artifactId>
   <version>1.18.30</version>
   <scope>provided</scope>
</dependency>
```

**Gradle:**
```groovy
compileOnly 'org.projectlombok:lombok:1.18.30'
annotationProcessor 'org.projectlombok:lombok:1.18.30'
```

---

### **Drawbacks**

- **Bytecode Manipulation:** Can occasionally cause issues with tooling or frameworks.
- **Less Explicit Code:** New developers unfamiliar with Lombok might find the "invisible" code confusing.
- **Compatibility Risks:** Rare issues may occur with certain Java versions or libraries.

---

### **Alternatives**

- **Java Records (Java 14+):** Native support for immutable data classes.
- **Immutables.io:** A popular annotation processor for generating immutable objects.
- **AutoValue (by Google):** Another code generation tool focused on immutable value types.

---