// frontend/src/services/api.js
// Mock-API: speichert Spiele lokal (localStorage) – KEIN Backend nötig!

const KEY = "trainer_games_v1";

function read() {
    const raw = localStorage.getItem(KEY);
    return raw ? JSON.parse(raw) : [];
}
function write(list) {
    localStorage.setItem(KEY, JSON.stringify(list));
}
function seedIfEmpty() {
    const list = read();
    if (list.length === 0) {
        const now = Date.now();
        const demo = [
            { id: 1, opponent: "SV Demo", dateTime: new Date(now + 60 * 60 * 1000).toISOString(), location: "Platz 1" },
            { id: 2, opponent: "FC Beispiel", dateTime: new Date(now + 24 * 60 * 60 * 1000).toISOString(), location: "Halle A" },
        ];
        write(demo);
    }
}
seedIfEmpty();

export const api = {
    async get(path) {
        if (path !== "/games") throw new Error("Unknown path " + path);
        return { data: read() };
    },
    async post(path, payload) {
        if (path !== "/games") throw new Error("Unknown path " + path);
        const list = read();
        const id = list.length ? Math.max(...list.map(g => g.id)) + 1 : 1;
        const game = { id, ...payload };
        list.push(game);
        write(list);
        return { data: game };
    },
    async put(path, payload) {
        const m = path.match(/^\/games\/(\d+)$/);
        if (!m) throw new Error("Unknown path " + path);
        const id = Number(m[1]);
        const list = read().filter(g => g.id !== id);
        const game = { id, ...payload };
        list.push(game);
        write(list);
        return { data: game };
    },
    async delete(path) {
        const m = path.match(/^\/games\/(\d+)$/);
        if (!m) throw new Error("Unknown path " + path);
        const id = Number(m[1]);
        const list = read().filter(g => g.id !== id);
        write(list);
        return { data: {} };
    },
};
