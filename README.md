# Polynomial Roots to Coefficients Solver

This project reads polynomial roots from a JSON input (file or direct input) and computes the polynomial coefficients.  
The output is given in **JSON format** for easy validation.

---

## 🚀 How to Compile and Run

### 1. Prerequisites
- Install **Java 8+**
- Download [Gson JAR](https://repo1.maven.org/maven2/com/google/code/gson/gson/2.11.0/gson-2.11.0.jar) and place it in the same folder as `PolynomialClass.java`

---

### 2. Compile
Open a terminal/command prompt in the project folder and run:

```bash
javac -cp ".;gson-2.11.0.jar" PolynomialClass.java



3. Run with JSON file

Example with input1.json:

java -cp ".;gson-2.11.0.jar" PolynomialClass input1.json


Or with input2.json:

java -cp ".;gson-2.11.0.jar" PolynomialClass input2.json

4. Run with direct JSON input

If you want to paste JSON directly instead of using a file:

java -cp ".;gson-2.11.0.jar" PolynomialClass


➡ Paste your JSON in the terminal
➡ Then press Ctrl+Z + Enter (Windows) or Ctrl+D (Linux/Mac) to end input.
