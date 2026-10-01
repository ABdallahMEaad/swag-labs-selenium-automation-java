# Swag Labs – Selenium + TestNG + Allure

أتمتة اختبارات موقع [Swag Labs](https://www.saucedemo.com/) باستخدام Java 21 و Selenium و TestNG و Allure، بنظام Page Object Model.

## هيكل المشروع

```
src/main/java/Page/...                 صفحات الموقع (Page Objects)
src/main/resources/config.json         إعدادات التشغيل (url, username, password, browserName)
src/test/resources/testdata.json       بيانات الـ Data Providers
src/test/java/Data/Data.java           الـ Data Providers (بتقرأ من testdata.json)
src/test/java/DriverFactory/...        تشغيل المتصفح (chrome / firefox / edge)
src/test/java/TestCases/...            الاختبارات
testng.xml                             Smoke Tests + Regression Tests (حسب الـ groups)
.github/workflows/tests.yml            الـ CI Pipeline
```

## المتطلبات

- Java 21
- Maven
- متصفح Chrome (أو Firefox / Edge حسب `browserName`)

## الإعدادات والداتا (JSON)

- `config.json`: غيّر `browserName` إلى `chrome` أو `firefox` أو `edge`.
- `testdata.json`: كل Data Provider له مفتاح بنفس اسمه (`credentials`, `invalidLoginDataWitherrorMessages`, `productsData`, `deliveryData` ...). عدّل الداتا من الملف بدون ما تلمس الكود.

## تشغيل الاختبارات

```bash
mvn clean test
```

`testng.xml` بيشغّل مجموعتين:

| Test | Group |
|------|-------|
| Smoke Tests | `smoke` |
| Regression Tests | `regression` |

لتشغيل مجموعة واحدة فقط امسح أو علّق الـ `<test>` الخاص بالمجموعة التانية في `testng.xml`.

## تقرير Allure

كل اختبار بياخد Screenshot بعد تنفيذه (نجح أو فشل) ويظهر جوه الاختبار نفسه في التقرير (مرفق باسم `Screenshot.png`).

بعد التشغيل:

```bash
npm install -g allure
allure generate target/allure-results -o allure-report --clean
allure open allure-report
```

النتايج بتتكتب في `target/allure-results` (مظبوطة في `src/test/resources/allure.properties`).

### قسم Environment في التقرير

بيتعمل تلقائياً بعد كل تشغيل (ملف `environment.properties` جوه فولدر النتايج) وفيه: اسم الـ Tester، تاريخ التشغيل، نظام التشغيل، المتصفح ونسخته، الـ Execution Mode، رابط الموقع، ونسخة Java. اسم الـ Tester بتغيّره من `tester` في `config.json`.

## رفع المشروع على GitHub وتشغيل الـ Pipeline

1. أنشئ Repository جديد على GitHub (فاضي، من غير README).
2. فك الضغط وافتح الـ Terminal جوه فولدر المشروع:

   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git branch -M main
   git remote add origin https://github.com/<USERNAME>/<REPO>.git
   git push -u origin main
   ```

3. بمجرد الـ push، الـ Pipeline بيشتغل لوحده من تبويب **Actions** (الملف: `.github/workflows/tests.yml`).
4. بعد انتهاء التشغيل نزّل تقرير Allure من **Artifacts** في صفحة الـ run (اسمه `allure-report`).

### عرض التقرير كرابط (GitHub Pages) – اختياري

الـ Pipeline بينشر التقرير على فرع `gh-pages` بعد أول تشغيل ناجح على الفرع الرئيسي. فعّله مرة واحدة:
**Settings → Pages → Source: Deploy from a branch → Branch: `gh-pages` / `(root)`**
وبعدها هتلاقي الرابط في نفس الصفحة.

### ملاحظات على الـ Pipeline

- بيشتغل على `ubuntu-latest` بمتصفح Chrome في وضع **headless** (من خلال `-Dheadless=true`). على جهازك المتصفح بيفتح عادي.
- بيشتغل عند كل `push` و `pull request`، ويمكن تشغيله يدوياً من **Actions → Swag Labs Tests → Run workflow**.
