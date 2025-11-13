# 📚 City Library Digital Management System

## 🏷️ Project Title
**City Library Digital Management System**

---

## 🧩 Problem Statement
The **City Central Library** wants to digitize its operations to make book management, membership services, and transaction tracking more efficient.  
They require a **Java-based application** that handles:
- Book records  
- Member details  
- Issue/Return transactions  
- Persistent data storage using **File Handling**  
- Dynamic data management using the **Java Collections Framework (JCF)**  

---

## 🎯 System Capabilities
The system should:
- ✅ Add new books and members  
- ✅ Issue and return books  
- ✅ Store and retrieve data from text/binary files  
- ✅ Use **Collections** for searching, sorting, and managing records efficiently  

---

## 🧠 Project Objectives
- Apply **File Handling** concepts to store and retrieve data from text/binary files.  
- Implement the **Java Collections Framework** (`List`, `Set`, `Map`, `Queue`) for dynamic record management.  
- Use **Comparable** and **Comparator** for sorting books and members.  
- Apply **Buffered I/O**, **Character Streams**, and **Byte Streams** for efficient file operations.  
- Implement **Generics** in collections for type safety.  

---

## 🏁 Learning Outcomes (COs)
| CO Code | Learning Outcome |
|----------|------------------|
| **CO4.1** | Use Java’s File Handling API for persistent data storage |
| **CO4.2** | Implement Java Collections Framework for dynamic data management |
| **CO4.3** | Demonstrate sorting and searching using Comparable/Comparator |
| **CO4.4** | Integrate I/O operations with collection-based data handling |

---

## 🧱 Class Design

### 🟦 Book Class
**Attributes:**
- `bookId` (Integer) – Unique ID  
- `title` (String)  
- `author` (String)  
- `category` (String)  
- `isIssued` (Boolean)

**Methods:**
- `displayBookDetails()` – Display book info  
- `markAsIssued()` – Set `isIssued = true`  
- `markAsReturned()` – Set `isIssued = false`  

---

### 🟩 Member Class
**Attributes:**
- `memberId` (Integer) – Unique ID  
- `name` (String)  
- `email` (String)  
- `issuedBooks` (List<Integer>) – List of issued book IDs  

**Methods:**
- `displayMemberDetails()` – Show member info  
- `addIssuedBook(int bookId)` – Add a book to issued list  
- `returnIssuedBook(int bookId)` – Remove a book from issued list  

---

### 🟨 LibraryManager Class
**Attributes:**
- `Map<Integer, Book> books`  
- `Map<Integer, Member> members`  

**Methods:**
- `addBook()` – Add new book & save to file  
- `addMember()` – Add new member & save to file  
- `issueBook()` – Issue a book and update records  
- `returnBook()` – Return a book  
- `searchBooks()` – Search by title, author, or category  
- `sortBooks()` – Sort by title/author using Comparable/Comparator  
- `loadFromFile()` – Load data at startup  
- `saveToFile()` – Save data before exit  

---

## 🗂️ File Handling Requirements
- Use `FileReader`/`FileWriter` for text files.  
- Use `BufferedReader`/`BufferedWriter` for efficient I/O.  
- Use `FileInputStream`/`FileOutputStream` for binary object storage *(optional advanced)*.  
- Auto-create files if missing.  
- Files used:
  - **books.txt** – Book records  
  - **members.txt** – Member records  

---

## 🧮 Collections Used
| Collection | Purpose |
|-------------|----------|
| **List** | Store issued book IDs for each member |
| **Set** | Maintain unique book categories |
| **Map** | Store book/member records using IDs |
| **Queue (optional)** | Maintain waiting list for popular books |
| **Comparable** | Sort books by title |
| **Comparator** | Sort books by author or category |

---

## ⚙️ Implementation Steps
1. Create `Book` and `Member` classes with required fields and methods.  
2. Implement `LibraryManager` using collections to store data in memory.  
3. Load existing data from files at program startup.  
4. Provide menu options for:  
   - Add  
   - Search  
   - Sort  
   - Issue/Return  
5. Save all updates to files after each operation.  
