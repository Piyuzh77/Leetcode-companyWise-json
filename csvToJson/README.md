
# CSV to JSON Converter for LeetCode Questions

This project processes a directory of CSV files containing LeetCode questions asked by various companies during hiring processes. The program converts the data into a JSON format, including details about the problems, their difficulty, and the list of companies that asked the question.

## **The File named output.json is the file you might be here for**

## **Features**
- Traverses a directory to locate all CSV files.
- Extracts the company name from the file name.
- Parses each CSV file to extract problem data.
- Aggregates unique problems and maintains a list of companies for each problem (avoiding duplicates).
- Saves the processed data as a JSON file.

---

## **Technologies Used**
- **Java 17+**
- **Lombok** for boilerplate code reduction.
- **OpenCSV** for parsing CSV files.
- **Jackson** for converting data into JSON.
- **Maven** for dependency management.

---

## **Project Structure**
```
src/
├── main/
│   ├── java/
│   │   ├── com.example/
│   │   │   ├── App.java           // Main class
│   |   │   ├── Problems.java      // Model for storing problem data
│   |   │   ├── EmptyFileException.java    
│   └── test/
pom.xml                            // Maven configuration file
```

---

## **Getting Started**

### **Prerequisites**
- Install [Java 17 or higher](https://www.oracle.com/java/technologies/javase-downloads.html).
- Install [Maven](https://maven.apache.org/install.html).

### **Setup**
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/csv-to-json.git
   cd csv-to-json
   ```

2. Install dependencies using Maven:
   ```bash
   mvn install
   ```

3. Place your CSV files in a directory (e.g., `LeetCode-Questions-CompanyWise-master`).

4. Modify the `App.java` file to set the correct paths:
   ```java
   String inputDirectory = "LeetCode-Questions-CompanyWise-master";
   String output = "output.json";
   ```

5. Run the program:
   ```bash
   mvn exec:java -Dexec.mainClass="com.example.App"
   ```

---

## **CSV File Format**
The program expects CSV files with the following columns:
- **ID**: Problem ID (unique for each problem).
- **Title**: Name of the problem.
- **Difficulty**: Difficulty level (e.g., Easy, Medium, Hard).
- **Frequency**: (Optional) Appearance frequency.
- **Leetcode Question Link**: URL of the problem.

### **Example CSV File**
```csv
ID,Title,Difficulty,Frequency,Leetcode Question Link
1234,Replace the Substring for Balanced String,Medium,0.33,https://leetcode.com/problems/replace-the-substring-for-balanced-string
5678,Two Sum,Easy,0.99,https://leetcode.com/problems/two-sum
```

---

## **Output JSON Format**
The generated JSON file will look like this:
```json
[
  {
    "problemNumber": "1234",
    "name": "Replace the Substring for Balanced String",
    "difficulty": "Medium",
    "url": "https://leetcode.com/problems/replace-the-substring-for-balanced-string",
    "companies": ["google", "microsoft"]
  },
  {
    "problemNumber": "1",
    "name": "Two Sum",
    "difficulty": "Easy",
    "url": "https://leetcode.com/problems/two-sum",
    "companies": ["meta", "amazon"]
  }
]
```

---

## **Error Handling**
- The program skips invalid or empty CSV files.
- Logs errors during CSV processing without interrupting the program.

---

## **Contributing**
1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add new feature"
   ```
4. Push to the branch:
   ```bash
   git push origin feature-name
   ```
5. Open a pull request.

---

---

## **Contact**
For issues or feature requests, please open an issue in the GitHub repository or contact the project maintainer.
