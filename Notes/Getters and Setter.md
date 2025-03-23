
---

### **Getters and Setters in Java**

**Getters and Setters**, also known as **accessor** and **mutator methods**, are fundamental in Java for controlling access to class variables. They are an essential part of **encapsulation**, which is a key principle of object-oriented programming (OOP).

---

### **Purpose**

- **Getters (Accessors):** Used to retrieve the value of a private variable.
- **Setters (Mutators):** Used to modify or update the value of a private variable.

By using getters and setters, you protect your data from **direct access** and allow you to:
- Enforce **validation rules**.
- Control how and when data is accessed or modified.
- Hide internal implementation details.

---

### **Why Encapsulation?**

Encapsulation helps:
- Protect sensitive data.
- Prevent external classes from putting an object into an invalid state.
- Improve code maintainability and security.

---

### **Java Example**

```java
public class Person {
    private String name;
    private int age;

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for age
    public int getAge() {
        return age;
    }

    // Setter for age with validation
    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        } else {
            System.out.println("Age cannot be negative.");
        }
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);

        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());

        person.setAge(-5); // Output: Age cannot be negative.
        System.out.println("Age: " + person.getAge()); // Output: Age: 30
    }
}
```

---

### **Explanation**

- The `name` and `age` variables are **private** (data hiding).
- `getName()` and `getAge()` allow **read access** to private fields.
- `setName()` and `setAge()` allow **write access**, but `setAge()` also enforces a rule: age cannot be negative.
- The `main()` method demonstrates how setters prevent invalid data entry (e.g., negative age).

---

### **Benefits of Getters & Setters**

✅ Protect fields from direct access  
✅ Centralize validation logic  
✅ Allow future flexibility (e.g., lazy loading, logging)  
✅ Follow OOP best practices  

---

### **Tip**

With **Lombok** (as we discussed earlier), you can simplify this with `@Getter` and `@Setter` annotations:

```java
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    private String name;
    private int age;
}
```
### **Why Avoid Setters?**

In some cases, you don't want your object's fields to be changed after the object is created. This is where **immutable classes** come in.

An **immutable class**:
- Has fields that cannot be modified after construction.
- Is thread-safe by design (no changes = no data races).
- Represents values that should remain constant (e.g., `String`, configuration objects, DTOs).

---

### **When to Avoid Setters?**

1. **Creating Immutable Objects:**  
   If you don't want the object's state to change after initialization.

2. **Concurrency Scenarios:**  
   Immutable objects help avoid synchronization issues in multi-threaded environments.

3. **Value Objects (Domain-Driven Design):**  
   Classes that represent a concept (e.g., `Money`, `Address`, `Coordinates`) are usually immutable.

4. **DTOs in APIs:**  
   For responses that should only carry data and never be altered post-creation.

---

### **How to Make an Immutable Class**

- Mark all fields as `private final`.
- Provide only **getters**, no setters.
- Initialize all fields via the constructor.
- Avoid exposing references to mutable objects.

---

### **Example of an Immutable Class**

```java
public final class Person {

    private final String name;
    private final int age;

    // Constructor initializes all fields
    public Person(String name, int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
        this.name = name;
        this.age = age;
    }

    // Only Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
```

### **Usage:**
```java
Person person = new Person("Alice", 25);
// person.setName() or person.setAge() doesn't exist!

System.out.println(person.getName()); // Alice
System.out.println(person.getAge());  // 25
```

---

### **Benefits of Immutable Classes**

✅ Safer in multi-threaded environments  
✅ Easier to debug and reason about  
✅ Promotes functional programming principles  
✅ Reduces chances of accidental modification

---

### **Lombok Shortcut:**

Lombok makes immutability easier with `@Value`:

```java
import lombok.Value;

@Value
public class Person {
    String name;
    int age;
}
```
- `@Value` automatically makes the class `final`, fields `private final`, generates **getters**, **constructor**, and **toString()** without setters.

---

### **Quick Comparison**

| Mutable Class (with setters) | Immutable Class (no setters) |
|------------------------------|------------------------------|
| Can change state after creation | Cannot change state once created |
| Needs validation inside setters | Validation done inside constructor |
| Risk of thread-safety issues | Naturally thread-safe |
| Example: Entity classes in JPA | Example: Value objects, DTOs |

---