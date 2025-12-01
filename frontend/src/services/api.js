// frontend/src/services/api.js
// Echte HTTP API mit fetch – funktioniert lokal und auf Render

// BASE_URL kommt aus Render-Umgebungsvariable,
// oder lokal wird localhost:8080 verwendet
// frontend/src/services/api.js
// Echte HTTP API mit fetch – funktioniert lokal und auf Render

// BASE_URL kommt aus Render-Umgebungsvariable,
// oder lokal wird localhost:8080 verwendet
const API_BASE_URL = "https://webtech-trainer-4.onrender.com/api/games";

export const api = {
    async get() {
        const res = await fetch(API_BASE_URL);
        const data = await res.json();
        return { data };
    },

    async post(payload) {
        const res = await fetch(API_BASE_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });
        const data = await res.json();
        return { data };
    }
};
