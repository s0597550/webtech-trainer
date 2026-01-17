/// frontend/src/services/api.js

// In Production kommt die URL aus Render (ENV)
// Lokal fällt es automatisch auf localhost:8080 zurück
const API_BASE_URL =
    import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080';

// Zentrale API-Funktionen für das Backend
export const api = {
    async get(path) {
        const res = await fetch(`${API_BASE_URL}${path}`);
        if (!res.ok) {
            throw new Error(`GET ${path} failed: ${res.status}`);
        }
        return await res.json();
    },

    async post(path, payload) {
        const res = await fetch(`${API_BASE_URL}${path}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: payload !== undefined ? JSON.stringify(payload) : null
        });

        if (!res.ok) {
            throw new Error(`POST ${path} failed: ${res.status}`);
        }

        // falls Backend nichts zurückgibt
        if (res.status === 204) return null;
        return await res.json();
    },

    async put(path, payload) {
        const res = await fetch(`${API_BASE_URL}${path}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: payload !== undefined ? JSON.stringify(payload) : null
        });

        if (!res.ok) {
            throw new Error(`PUT ${path} failed: ${res.status}`);
        }

        if (res.status === 204) return null;
        return await res.json();
    },

    async delete(path) {
        const res = await fetch(`${API_BASE_URL}${path}`, {
            method: 'DELETE'
        });

        if (!res.ok) {
            throw new Error(`DELETE ${path} failed: ${res.status}`);
        }

        return null;
    }
};
