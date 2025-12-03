
# Emergency Waitlist – Hospital Triage Web App

A simple full-stack web app that lets **patients register for emergency triage**, see their **estimated wait time**, and lets **admins view & reprioritize** the queue.  
Built with **Node.js + Express + PostgreSQL** and vanilla **HTML/CSS/JS**.

---

## 1. Project Structure

```text
emergency-waitlist-1/
├─ server.js                      # Express server + REST API
├─ db.js                          # PostgreSQL connection (pg Pool)
├─ package.json
├─ public/                        # Front-end pages & styles
│  ├─ styles.css
│  ├─ images/
│  │   ├─ triage.png
│  │   ├─ button_style.png
│  │   └─ color_palette.png
│  ├─ patient_home.html
│  ├─ patient_register.html
│  ├─ patient_status.html
│  ├─ patient_info.html
│  ├─ admin_home.html
│  ├─ admin_update_priority.html
│  └─ admin_patient_info.html
└─ node_modules/
 ```

---

## 2. Technology Stack
- Backend: Node.js, Express, pg
- Database: PostgreSQL
- Frontend: HTML5, CSS, JavaScript
- Tools: pgAdmin for DB management

---

## 3. Database Setup (PostgreSQL)

```sql
CREATE DATABASE emergency_waitlist;

CREATE TABLE levels (
triage_level_code SMALLINT PRIMARY KEY,
label             TEXT NOT NULL
);

INSERT INTO levels (triage_level_code, label) VALUES
(1, 'Level 1'),
(2, 'Level 2'),
(3, 'Level 3');

CREATE TABLE patients (
id                SERIAL PRIMARY KEY,
name              TEXT NOT NULL,
age               INTEGER CHECK (age >= 0),
symptoms          TEXT,
pain_level        INTEGER CHECK (pain_level BETWEEN 1 AND 10),
notes             TEXT,
triage_level_code SMALLINT NOT NULL REFERENCES levels (triage_level_code),
status            TEXT NOT NULL DEFAULT 'waiting',
created_at        TIMESTAMPTZ NOT NULL DEFAULT NOW(),
updated_at        TIMESTAMPTZ NOT NULL DEFAULT NOW()
);
```
 
---

## 4. Configure Database Connection

Modify **db.js**:

```js
const { Pool } = require("pg");

const pool = new Pool({
  host: "127.0.0.1",
  port: 5432,
  user: "postgres",
  password: "YOUR_PASSWORD",
  database: "emergency_waitlist",
});

module.exports = pool;

```
 
---

## 5. Install & Run
- npm install
- node server.js
- Server runs at:

- 👉 http://localhost:3000

---

## 6. How the App Works

## 🧑‍🤝‍🧑 Patient Flow

| **Page**              | **Function**                  |
|----------------------|------------------------------|
| `patient_home.html`     | Show estimated wait time      |
| `patient_register.html` | Register & receive patient ID |
| `patient_status.html`   | Check wait time with ID       |
| `patient_info.html`     | View personal info            |

## 🩺 Admin Flow

| **Page**                     | **Function**                             |
|-----------------------------|-------------------------------------------|
| `admin_home.html`              | View queue list                           |
| `admin_patient_info.html`      | Full info of a patient                    |
| `admin_update_priority.html`   | Update triage level or mark completed     |
 
---

## 7. Backend API (Express)

### **Register Patient**
**POST** `/api/patients`

---

### **Queue List (Admin)**
**GET** `/api/patients`

---

### **Patient Details**
**GET** `/api/patients/:id`

---

### **Patient Status**
**GET** `/api/patients/:id/status`

---

### **Update Priority / Status**
**PATCH** `/api/patients/:id`
 
---

## 8. Triage & Wait Time Logic

### **Triage Level Based on Pain Score**

| Pain Level | Triage Level |
|-----------|--------------|
| **8–10**  | Level 1 (highest priority) |
| **5–7**   | Level 2 |
| **1–4**   | Level 3 |
 
---

## 9. Demo Instructions
- Ensure PostgreSQL is running
- Create DB + tables (Section 3)
- Update db.js with your password
- Run:
        npm install
        node server.js
- Visit:

- Patient home → http://localhost:3000/patient_home.html
- Admin home → http://localhost:3000/admin_home.html

---

## 10. Future Improvements
- Add admin login/authentication
- Add triage history logs
- Real-time updates (WebSockets)
- Mobile app version

---

## Summary

This project demonstrates:
- Backend + database integration
- Dynamic triage prioritization
- REST API design
- Front-end + backend interaction
- Suitable for assignments, demos, and portfolio use

---

## Author
- Tong Zhou
- Nachi Duong
