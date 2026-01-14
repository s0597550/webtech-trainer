<template>
  <section class="players">
    <h2>Spielerliste</h2>

    <form class="add" @submit.prevent="addPlayer">
      <input
          v-model="newName"
          placeholder="Spielername"
          required
      />
      <button>Hinzufügen</button>
    </form>

    <ul>
      <li v-for="p in players" :key="p.id">
        <strong>{{ p.name }}</strong>

        <select v-model="p.status" @change="updateStatus(p)">
          <option>Anwesend</option>
          <option>Krank</option>
          <option>Verletzt</option>
          <option>Urlaub</option>
        </select>

        <button class="danger" @click="remove(p.id)">✖</button>
      </li>
    </ul>
  </section>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const players = ref([])
const newName = ref('')

const API = 'http://localhost:8080/api/players'

async function load() {
  const res = await fetch(API)
  players.value = await res.json()
}

async function addPlayer() {
  await fetch(API, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ name: newName.value, status: 'Anwesend' })
  })
  newName.value = ''
  load()
}

async function updateStatus(player) {
  await fetch(`${API}/${player.id}/status`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(player.status)
  })
  load()
}

async function remove(id) {
  await fetch(`${API}/${id}`, { method: 'DELETE' })
  load()
}

onMounted(load)
</script>

<style scoped>
.players {
  margin-top: 2rem;
}

.add {
  display: flex;
  gap: .5rem;
  margin-bottom: 1rem;
}

ul {
  list-style: none;
  padding: 0;
}

li {
  display: flex;
  align-items: center;
  gap: .75rem;
  padding: .5rem;
  border: 1px solid #e5e7eb;
  border-radius: .5rem;
  margin-bottom: .5rem;
}

select {
  margin-left: auto;
}

button {
  padding: .3rem .6rem;
  border-radius: .4rem;
  border: 1px solid #e5e7eb;
  cursor: pointer;
}

button.danger {
  color: #b91c1c;
  border-color: #fecaca;
}
</style>
