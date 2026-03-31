# Departmental Procurement System

Simple Java web application for departmental procurement workflows using Servlet + JSP + SQLite.

## Stack

- Java 17
- Maven (WAR packaging)
- Tomcat 10.1+ (Jakarta EE 9/10 compatible)
- Jakarta Servlet 5.0 + JSTL 2.0
- SQLite (xerial sqlite-jdbc)
- JSP + Tailwind CSS (CDN)

## What This Project Contains

- Dashboard and 8 modules:
    - Departments
    - Suppliers
    - Items
    - Requisitions
    - Quotations
    - Purchase Orders
    - Goods Receipts
    - Payments
- Domain-based structure under src/main/java/com/example/procurement/features
- Shared config/util/listener classes under src/main/java/com/example/procurement/shared
- JSP views under src/main/webapp/WEB-INF/views

## Current Implementation Status

- Departments:
    - Full CRUD implemented in DAO + Service + Servlet + JSP.
- Other 7 modules:
    - DAO and Service: full CRUD methods implemented.
    - Servlet POST and form JSP: intentionally left as guided placeholders with direct implementation instructions.
    - List page rendering is available.

## Database

- Database engine: SQLite
- Default file: procurement.db (project root at runtime)
- Override path using JVM system property:

```bash
-Dprocurement.db.path=/absolute/path/to/procurement.db
```

- Schema is auto-created on app startup by:
    - com.example.procurement.shared.listener.ApplicationStartupListener
    - com.example.procurement.shared.config.DatabaseInitializer

No manual SQL import is required.

## Build

```bash
mvn clean package
```

WAR output:

```text
target/procurement.war
```

## Deploy To Tomcat 10

1. Copy target/procurement.war to TOMCAT_HOME/webapps.
2. Start Tomcat.
3. Open:

```text
http://localhost:8080/procurement/
```

If you see stale JSP behavior, clear Tomcat cache folders (work and old exploded app) and redeploy.

## Main Routes

- /dashboard
- /departments and /departments/form
- /suppliers and /suppliers/form
- /items and /items/form
- /requisitions and /requisitions/form
- /quotations and /quotations/form
- /purchase-orders and /purchase-orders/form
- /goods-receipts and /goods-receipts/form
- /payments and /payments/form

## UI Behavior

- Module list pages use a side-by-side layout:
    - Left: table/list
    - Right: form panel (embedded form view)
- Form pages can also be opened directly via /module/form.

## Project Layout (Simplified)

```text
src/main/java/com/example/procurement/
    features/
        <module>/
            dao/
            model/
            service/
            servlet/
    shared/
        config/
        exception/
        listener/
        util/

src/main/webapp/
    assets/
    WEB-INF/
        web.xml
        views/
            components/
            dashboard.jsp
            errors/
            <module>/
                list.jsp
                form.jsp
```

## Notes

- This project intentionally keeps logic simple and readable.
- For non-department modules, follow the direct implementation instructions in servlet doPost and form.jsp to complete UI submit flows.
