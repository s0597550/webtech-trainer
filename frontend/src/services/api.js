// frontend/src/services/api.js
// Echte HTTP API mit fetch – funktioniert lokal und auf Render

// BASE_URL kommt aus Render-Umgebungsvariable,
// oder lokal wird localhost:8080 verwendet
const BASE_URL =
    import.meta.env.VITE_API_BASE_URL || "http://localhost:8080/api";

async function request(path, options = {}) {
    const res = await fetch(`${BASE_URL}${path}`, {
        headers: { "Content-Type": "application/json" },
        ...options,
    });

    if (!res.ok) {
        throw new Error(`Request failed with status ${res.status}`);
    }

    const data = await res.json();
    return { data }; // damit dein App.vue Code mit { data } weiter funktioniert
}

export const api = {
    get(path) {
        return request(path);
    },
    post(path, payload) {
        return request(path, {
            method: "POST",
            body: JSON.stringify(payload),
        });
    },
    put(path, payload) {
        return request(path, {
            method: "PUT",
            body: JSON.stringify(payload),
        });
    },
    delete(path) {
        return request(path, {
            method: "DELETE",
        });
    },
};
