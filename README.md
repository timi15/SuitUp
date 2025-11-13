# 👔 SuitUp!

A **SuitUp!** egy webalkalmazás, amely lehetővé teszi a felhasználók számára, hogy saját ruhadarabjaikat feltöltsék, és ezekből különböző **outfiteket** állítsanak össze.  
A projektet három fős csapat fejleszti a **Szoftverfejlesztési módszertanok** egyetemi tantárgy keretében.

---

## 🧩 Projekt célja

A cél egy olyan platform létrehozása, ahol a felhasználók digitálisan rendszerezhetik ruhatárukat, inspirációt kaphatnak az összeállításokhoz, és könnyen átláthatják, mely ruhadarabjaikat használják a leggyakrabban.

---

## 🔑 Fő funkciók

- 👤 **Regisztráció és bejelentkezés**  
  A felhasználó saját fiókot hozhat létre, majd bejelentkezés után kezelheti ruhadarabjait és outfiteket.

- 👕 **Ruhadarabok feltöltése**  
  A ruhadarabokat nem képfájlként, hanem **link formájában** lehet feltölteni (pl. egy online kép URL-je).

- 🧍‍♂️ **Outfitek összeállítása**  
  A feltöltött ruhadarabokból a felhasználó saját összeállításokat hozhat létre.

- 🌤️ **Főoldal funkciói**
    - Az **aktuális évszakhoz illő ruhadarabok** jelennek meg (nem AI-ajánlás alapján).
    - Megjelenik a **10 legnépszerűbb ruhadarab**, amelyet a felhasználók a legtöbb outfitben használtak.

---

## ⚙️ Használt technológiák

| Technológia | Szerepe |
|--------------|----------|
| **Spring Boot** | Backend (üzleti logika, adatkezelés) |
| **Thymeleaf** | Frontend (szerveroldali sablonkezelés) |
| **PostgreSQL** | Adatbázis (felhasználók, ruhadarabok, outfitek tárolása) |

---

## 🧱 Architektúra

A projekt **háromrétegű architektúrát** követ:

- **Frontend:** Thymeleaf sablonok (HTML + CSS + JavaScript)
- **Backend:** Spring Boot
- **Adatbázis:** PostgreSQL
    - Ruhadarabok, outfitek és felhasználói adatok tárolása
    - Képek helyett csak **kép URL-ek** kerülnek mentésre

---

## 🧑‍💻 Fejlesztői csapat

- **[Kántor Kamilla](https://github.com/kericica)** – QA, PA
- **[Szőllős Boglárka](https://github.com/SzollosBoglarka)** – UI/UX Designer, PA
- **[Varga Tímea](https://github.com/timi15)** – PM, PA

---

## 🚀 Projekt futtatása (fejlesztői környezetben)

1. **Klónozd a projektet:**
   ```bash
   git clone https://github.com/timi15/SuitUp.git
   cd suitup
   ```

2. **Adatbázis beállítása:**
    - Hozz létre egy PostgreSQL adatbázist (pl. `suitup_db`)
    - Frissítsd az `application.properties` fájlban a kapcsolati adatokat.

3. **Backend indítása:**
   ```bash
   mvn spring-boot:run
   ```

4. **Alkalmazás elérése:**  
   Nyisd meg a böngészőben: [http://localhost:8080](http://localhost:8080)

---

## 📎 Kapcsolódó repó

A projekt dokumentációja itt érhető el:  
👉 **[SuitUp (dokumentáció)](https://github.com/kericica/SuitUp_documentation.git)**

---

## 📄 Licenc

A dokumentáció az **MIT licenc** alatt érhető el.

