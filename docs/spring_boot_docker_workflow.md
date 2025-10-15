# Spring Boot Docker Live-Update Workflow

This workflow ensures your Spring Boot applications run correctly from compiled classes and dependencies inside Docker without using a fat jar.

---

## 1. Build Dependencies (Step 1)

```bash
mvn dependency:copy-dependencies -DoutputDirectory=target/dependency
```

- Copies all Maven dependencies (Spring Boot, etc.) into `target/dependency`.  
- Run whenever you add or update dependencies in `pom.xml`.

---

## 2. Compile Project Classes (Step 2)

```bash
mvn clean compile
```

- Compiles Java code to `target/classes`.  
- Run whenever you change Java source code.

---

## 3. Dockerfile Configuration

```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-cp", "/app/classes:/app/dependency/*", "org.crm.CrmServiceApplication"]
```

- Mount `target/classes` and `target/dependency` in `docker-compose.yml` for live updates.
- Do not mount `src`.

---

## 4. docker-compose.yml Volumes (Example)

```yaml
volumes:
  - ./crm-service/target/classes:/app/classes
  - ./crm-service/target/dependency:/app/dependency
```

- Repeat for `user-service`.

---

## 5. Startup Workflow

1. Update dependencies if changed:

```bash
mvn dependency:copy-dependencies -DoutputDirectory=target/dependency
```

2. Compile classes:

```bash
mvn clean compile
```

3. Restart Docker containers:

```bash
docker compose up -d
```

- Ensures container runs latest code with all required jars.

---

## 6. Notes / Tips

- Run Step 1 after adding new Maven dependencies.
- Run Step 2 for code-only changes.
- Avoid hardcoding passwords in SQL.
- Ensure MySQL container is healthy before Spring Boot services start.

---

✅ This workflow ensures:
- No `NoClassDefFoundError`
- Hot updates without rebuilding Docker images
- Safe handling of credentials

