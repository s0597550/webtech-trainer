<template>
  <main class="container">
    <header>
      <h1>⚽ Trainer-Website</h1>
      <p>Spiele im Kalender eintragen und verwalten</p>
    </header>

    <section class="layout">
      <GameForm
          :game="editingGame"
          @saved="onSaved"
      />

      <GameList
          :games="games"
          :loading="loading"
          @remove="deleteGame"
          @edit="editGame"
          @reload="loadGames"
      />
    </section>

    <!-- 👥 SPIELERLISTE -->
    <section style="margin-top: 2rem">
      <PlayerList />
    </section>
  </main>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { api } from './services/api'
import GameList from './components/GameList.vue'
import GameForm from './components/GameForm.vue'
import PlayerList from './components/PlayerList.vue'
import GameStats from './components/GameStats.vue'

const games = ref([])
const loading = ref(false)

/* ======= NUR ERGÄNZT ======= */
const editingGame = ref(null)
/* ========================== */

async function loadGames() {
  loading.value = true
  try {
    const data = await api.get('/api/games')
    games.value = data
  } catch (e) {
    console.error(e)
    alert('Konnte Spiele nicht laden')
  } finally {
    loading.value = false
  }
}

/* =========================
   BESTEHEND + ERWEITERT
========================= */
async function deleteGame(id) {
  if (!confirm('Spiel wirklich löschen?')) return
  await api.delete(`/api/games/${id}`)
  loadGames()
}

/* ======= NUR ERSETZT ======= */
function editGame(game) {
  editingGame.value = game
}

function onSaved() {
  editingGame.value = null
  loadGames()
}
/* ========================== */

onMounted(loadGames)
</script>

<style scoped>
.container { margin: 0 auto; max-width: 980px; padding: 2rem 1rem 4rem; font-family: system-ui,-apple-system,Segoe UI,Roboto,Helvetica,Arial,sans-serif; }
header { margin-bottom: 1.5rem; border-bottom: 1px solid #e5e7eb; }
h1 { margin: 0 0 .25rem; font-size: 1.9rem; }
.layout { display: grid; grid-template-columns: 380px 1fr; gap: 1rem; align-items: start; }
@media (max-width: 900px){ .layout { grid-template-columns: 1fr; } }
</style>
