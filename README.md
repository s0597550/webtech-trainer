# Webtechnologien – Projekt (M1)

**Thema:** Trainer-Website – Spiele im Kalender eintragen und verwalten
**Beschreibung:** Trainer können ihre Spiele verwalten, z. B. Gegner, Datum/Uhrzeit und Ort hinzufügen, bearbeiten und anzeigen.


## M1-Status
- [x] GitHub-Repo
- [x] Entity-Klasse überlegt (`Game`: id, opponent, dateTime, location)
- [x] Spring-App mit GET-Route `/api/notes/hello`

## M2-Status ✅

- ✅ Vue-App erstellt (Vite)
- ✅ Eigene Unterkomponente `GameItem.vue`
- ✅ Rendering per `v-for` in `GameList.vue`
- ✅ Neues Spiel anlegen
- ✅ Spiele anzeigen
- ✅ Bearbeiten & Löschen möglich
- ✅ Mock-API implementiert (`frontend/src/services/api.js`)
- ✅ Code auf GitHub gepusht (`/frontend`)

## M3-Status ✅

### ✔ Backend (Spring Boot)
- REST-API mit `/api/games` vollständig implementiert  
- Backend läuft lokal auf Port **8080**
- GET-Route liefert gültiges JSON (z. B. `[]` bei leerer Liste)
- POST/PUT/DELETE für Spiele funktionieren lokal
- Dockerfile hinzugefügt für Render-Deployment

### ✔ Frontend (Vue.js)
- Vue-Single-Page-Application läuft lokal auf Port **5173**
- Frontend ruft die echte Backend-API `/api/games` per Fetch/Ajax auf
- Spiele können lokal erstellt, angezeigt, bearbeitet und gelöscht werden
- Mock-API wurde vollständig durch echte REST-API ersetzt

### ✔ Verbindung Frontend → Backend
- Frontend lädt beim Start die Spieleliste über:

GET http://localhost:8080/api/games
