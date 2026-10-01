# 🧪 Swag Labs – Selenium Test Automation

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Selenium](https://img.shields.io/badge/Selenium-4.x-43B02A?logo=selenium)
![TestNG](https://img.shields.io/badge/TestNG-7.x-red)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?logo=apachemaven)
![Allure](https://img.shields.io/badge/Allure-Report-FF6A00)
![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-CI-blue?logo=githubactions)

A professional **Web UI Test Automation Framework** for [Swag Labs](https://www.saucedemo.com/) built with **Java 21, Selenium WebDriver, TestNG, Maven, and Allure** using the **Page Object Model (POM)** design pattern.

The project demonstrates a scalable automation structure with:

* 🔐 Login testing
* 🛒 Product and cart testing
* 💳 Checkout testing
* 🔄 Product sorting validation
* 📊 Data-driven testing
* 🧪 Smoke & Regression test groups
* 📸 Automatic screenshots
* 📈 Allure reporting
* ⚙️ Multi-browser support
* 🚀 GitHub Actions CI/CD

---

## 🛠️ Tech Stack

| Technology                     | Purpose                          |
| ------------------------------ | -------------------------------- |
| ☕ **Java 21**                  | Programming Language             |
| 🕸️ **Selenium WebDriver**     | Web UI Automation                |
| 🧪 **TestNG**                  | Test Execution & Test Management |
| 📦 **Maven**                   | Build & Dependency Management    |
| 📊 **Allure**                  | Test Reporting                   |
| 🗂️ **JSON**                   | Configuration & Test Data        |
| 🌐 **Chrome / Firefox / Edge** | Cross-Browser Testing            |
| 🚀 **GitHub Actions**          | CI/CD Automation                 |
| 🏗️ **Page Object Model**      | Framework Design Pattern         |

---

## 📁 Project Structure

```text
SwagLabs/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── Page/
│   │   │       └── ...
│   │   │
│   │   └── resources/
│   │       └── config.json
│   │
│   └── test/
│       ├── java/
│       │   ├── Data/
│       │   │   └── Data.java
│       │   │
│       │   ├── DriverFactory/
│       │   │   └── ...
│       │   │
│       │   └── TestCases/
│       │       └── ...
│       │
│       └── resources/
│           ├── testdata.json
│           └── allure.properties
│
├── .github/
│   └── workflows/
│       └── tests.yml
│
├── testng.xml
├── pom.xml
└── README.md
```

---

## 🧩 Framework Architecture

The framework follows the **Page Object Model (POM)** to separate test logic from page-specific actions and locators.

```text
                    ┌─────────────────────┐
                    │     Test Cases      │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │    Page Objects     │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │   Base Page / Web   │
                    │     Interactions    │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │  Selenium WebDriver │
                    └─────────────────────┘
```

### 📌 Main Components

**Page Objects**

* Store locators and page-specific actions.
* Keep test cases clean and readable.

**Driver Factory**

* Handles WebDriver initialization.
* Supports:

  * 🌐 Chrome
  * 🦊 Firefox
  * 🔷 Edge

**Data Providers**

* Read test data from `testdata.json`.
* Support data-driven testing without modifying Java code.

**TestNG**

* Handles test execution.
* Supports groups such as:

  * 🔥 Smoke
  * 🔄 Regression

**Allure**

* Generates detailed execution reports.
* Includes screenshots and test metadata.

---

## ⚙️ Configuration

### `config.json`

Browser and execution settings are managed through the configuration file.

```json
{
  "url": "https://www.saucedemo.com/",
  "username": "standard_user",
  "password": "secret_sauce",
  "browserName": "chrome",
  "tester": "Abdallah MEaad"
}
```

### 🌐 Supported Browsers

Change `browserName` to:

```text
chrome
firefox
edge
```

---

## 🗃️ Test Data

Test data is stored separately inside:

```text
src/test/resources/testdata.json
```

Each Data Provider uses a corresponding key in the JSON file.

Example:

```text
credentials
invalidLoginDataWitherrorMessages
productsData
deliveryData
```

This allows test data to be updated without changing the automation code.

---

## 🧪 Test Coverage

The framework covers several important Swag Labs scenarios.

### 🔐 Authentication

* ✅ Valid Login
* ❌ Invalid Login
* ⚠️ Login Validation & Error Messages

### 🛍️ Products

* ✅ Add Product to Cart
* ✅ Remove Product from Cart
* ✅ Product Sorting
* 🔤 Sort A → Z
* 🔤 Sort Z → A
* 💰 Sort Price Low → High
* 💰 Sort Price High → Low

### 🛒 Shopping Cart

* ✅ Verify Cart Items
* ✅ Verify Product Quantity
* ✅ Remove Products
* ✅ Verify Cart Badge

### 💳 Checkout

* ✅ Enter Customer Information
* ✅ Continue Checkout
* ✅ Verify Checkout Overview
* ✅ Complete Order
* ✅ Verify Order Confirmation

---

## 🏷️ Test Groups

Tests are organized using TestNG groups.

| Test Suite          | Group        | Purpose                  |
| ------------------- | ------------ | ------------------------ |
| 🔥 Smoke Tests      | `smoke`      | Critical functionality   |
| 🔄 Regression Tests | `regression` | Full functional coverage |

---

## ▶️ Run Tests Locally

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/<USERNAME>/<REPOSITORY>.git
```

### 2️⃣ Navigate to the Project

```bash
cd SwagLabs
```

### 3️⃣ Run All Tests

```bash
mvn clean test
```

### 4️⃣ Run TestNG Suite

```bash
mvn test
```

---

## 📊 Allure Report

Allure results are generated inside:

```text
allure-results
```

Generate the report:

```bash
allure generate target/allure-results -o allure-report --clean
```

Open the report:

```bash
allure open allure-report
```

### 📸 Screenshots

Each test captures a screenshot after execution.

The screenshot is attached directly to the corresponding test in the Allure report:

```text
Screenshot.png
```

This makes it easier to investigate failed or unexpected test results.

---

## 🌍 Environment Information

The Allure report automatically includes an `environment.properties` file containing execution information such as:

* 👨‍💻 Tester
* 📅 Execution Date
* 💻 Operating System
* 🌐 Browser
* 🔢 Browser Version
* ⚙️ Execution Mode
* 🔗 Application URL
* ☕ Java Version

The tester name can be configured through:

```text
config.json
```

---

## 🚀 CI/CD – GitHub Actions

The project includes a GitHub Actions workflow:

```text
.github/workflows/tests.yml
```

### 🔄 Pipeline Flow

```text
       Push / Pull Request
                │
                ▼
        GitHub Actions
                │
                ▼
        Setup Java 21
                │
                ▼
          Install Maven
                │
                ▼
       Start Chrome Headless
                │
                ▼
          Run TestNG
                │
                ▼
        Generate Allure
                │
                ▼
       Upload Test Report
                │
                ▼
       GitHub Pages Deploy
```

### ⚙️ Pipeline Features

* 🚀 Runs automatically on every `push`
* 🔀 Runs on every `pull request`
* ▶️ Supports manual execution
* ☕ Uses Java 21
* 🌐 Runs Chrome in headless mode
* 🧪 Executes automated tests
* 📊 Generates Allure reports
* 📦 Uploads Allure report as an artifact
* 🌍 Can publish the report through GitHub Pages

---

## 🖥️ Local vs CI Execution

| Environment       | Browser Mode    |
| ----------------- | --------------- |
| 💻 Local Machine  | Normal Browser  |
| ☁️ GitHub Actions | Headless Chrome |

GitHub Actions runs the tests using:

```bash
-Dheadless=true
```

So no visible browser window is required in the CI environment.

---

## 📦 Allure Report Artifact

After the GitHub Actions workflow finishes:

```text
GitHub
  ↓
Actions
  ↓
Swag Labs Tests
  ↓
Workflow Run
  ↓
Artifacts
  ↓
allure-report
```

Download the generated Allure report from the workflow artifacts.

---

## 🌐 GitHub Pages

The pipeline can publish the Allure report to the `gh-pages` branch after a successful execution on the main branch.

To enable GitHub Pages:

```text
Repository
   ↓
Settings
   ↓
Pages
   ↓
Source
   ↓
Deploy from a branch
   ↓
Branch: gh-pages
   ↓
Folder: / (root)
```

After enabling it, GitHub will provide a public URL for the generated report.

---

## 🎯 Key Automation Practices

This project demonstrates several real-world automation practices:

* 🏗️ Page Object Model
* ♻️ Reusable page methods
* 🧪 Data-driven testing
* 🏷️ TestNG groups
* 🔧 Centralized WebDriver management
* 🌐 Multi-browser execution
* 📸 Automatic screenshots
* 📊 Allure reporting
* ⚙️ Externalized configuration
* 🗃️ Externalized test data
* 🚀 CI/CD with GitHub Actions
* 🔄 Smoke & Regression execution

---

## 📈 Future Improvements

Possible future enhancements:

* 🔹 Parallel test execution
* 🔹 Retry failed tests
* 🔹 Explicit Wait utility
* 🔹 Enhanced logging
* 🔹 Docker execution
* 🔹 Selenium Grid
* 🔹 Cross-browser CI matrix
* 🔹 API automation integration
* 🔹 Performance testing with JMeter

---

## 👨‍💻 Author

### Abdallah MEaad

**QA / QC Engineer | Manual & Automation Testing**

🔗 **Portfolio:** [Abdallah MEaad](https://abdallahmeaad.github.io/My_CV)

🔗 **LinkedIn:** [abdallah-meaad](https://www.linkedin.com/in/abdallah-meaad/)

🔗 **GitHub:** [ABdallahMEaad](https://github.com/ABdallahMEaad)

---

## ⭐ Project

If you find this automation framework useful, consider giving the repository a ⭐.

**Happy Testing! 🧪🚀**
