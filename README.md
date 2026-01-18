# Webtechnologien – Projekt (M1)

**Thema:** Trainer-Website – Spiele im Kalender eintragen und verwalten, Team Verwaltung.
**Beschreibung:** Trainer können ihre Spiele verwalten, z. B. Gegner, Datum/Uhrzeit und Ort hinzufügen, bearbeiten und anzeigen. Spieler hinzufügen , wobei man dann die Anwesenheit der Spieler angeben kann. Zu/Absagen für die angesetzten Spiele , Spiel Ergebnis eintragen + Siege/Niederlagen/Unentschieden Statistik.



M1 – Projektstart & Backend-Grundlage
Deadline: 19. Oktober
 - Projektthema festgelegt (Trainer- / Spielverwaltungs-App)
 - GitHub-Repository erstellt
 - Entity-Klasse Game entworfen
        - Felder: id, opponent, dateTime, location
 - Spring-Boot-Projekt initialisiert
 - Erste GET-Route implementiert und gepusht
- GET /api/games

M2 – Frontend mit Vue.js
Deadline: 9. November
 - Vue.js-App mit Vite erstellt
 - Eigene Unterkomponenten implementiert:
           - GameItem.vue
           - GameList.vue
           - GameForm.vue
- Dynamisches Rendering mit v-for
- Spiele können:
           - angelegt
           - angezeigt
           - bearbeitet
           - gelöscht werden
 - Zentrale API-Schicht (frontend/src/services/api.js)
 - Code vollständig auf GitHub gepusht


M3 – Deployment (Frontend & Backend)
Deadline: 23. November

 Backend (Spring Boot)
- REST-API vollständig implementiert:
     - GET /api/games
     -POST /api/games
    - PUT /api/games/{id}
    - DELETE /api/games/{id}
- Dockerfile für Deployment erstellt
- Backend erfolgreich auf Render deployed
- Backend ist öffentlich erreichbar
  
  Frontend (Vue.js)
- Single-Page-Application auf Render deployed
- Frontend kommuniziert per Fetch/Ajax mit Backend
- Frontend ruft beim Start die API auf:
    - GET/api/games
 
  Verbindung Frontend ↔ Backend
- Spiele werden live vom Backend geladen
- Änderungen im Frontend werden sofort im Backend gespeichert

M4 – Persistenz & vollständige REST-Funktionalität
Deadline: 14. Dezember
 - Backend speichert Daten persistent in PostgreSQL
 - Spiele werden in der Datenbank gespeichert
 - Frontend nutzt aktiv:
     - POST /api/games (Spiel anlegen)
     - PUT /api/games/{id} (Spiel bearbeiten)
     - PUT /api/games/{id}/result (Ergebnis setzen)
 - Zusagen / Absagen implementiert:
     - POST /api/games/{id}/like
     - POST /api/games/{id}/dislike
 - Ergebnis-Eingabe (z. B. 2:1)
 - Statistik (Siege / Unentschieden / Niederlagen)
      - wird dynamisch im Frontend aus dem Ergebnis berechnet
 - Voll funktionsfähige Web-App auf Render deployed
