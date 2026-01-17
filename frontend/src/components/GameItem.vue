<template>
  <li class="item">
    <div class="main">
      <strong>{{ game.opponent }}</strong>
      <span>•</span>
      <time :datetime="game.kickoff">{{ formatDate(game.kickoff) }}</time>
      <span>•</span>
      <span>{{ game.location }}</span>

      <!-- NEU: LIKE / DISLIKE ANZEIGE -->
      <span class="votes">
        👍 {{ game.likes }} | 👎 {{ game.dislikes }}
      </span>

      <!-- NEU: ERGEBNIS ANZEIGE -->
      <span v-if="game.result" class="result-text">
        🏁 {{ game.result }}
      </span>
    </div>

    <div class="actions">
      <!-- NEU: ZUSAGEN / ABSAGEN -->
      <button class="ok" @click="likeGame">👍</button>
      <button class="no" @click="dislikeGame">👎</button>

      <!-- NEU: ERGEBNIS EINTRAGEN -->
      <input
          v-model="result"
          placeholder="Ergebnis z.B. 2:1"
          class="result-input"
      />
      <button @click="saveResult">Speichern</button>

      <!-- BESTEHEND -->
      <button @click="$emit('edit', game)">Bearbeiten</button>
      <button class="danger" @click="$emit('remove')">Löschen</button>
    </div>
  </li>
</template>

<script setup>
import { ref } from 'vue'
import { api } from '../services/api.js'

const props = defineProps({
  game: { type: Object, required: true }
})

const emit = defineEmits(['reload'])

const result = ref(props.game.result || '')

async function likeGame() {
  await api.post(`/api/games/${props.game.id}/like`)
  emit('reload')
}

async function dislikeGame() {
  await api.post(`/api/games/${props.game.id}/dislike`)
  emit('reload')
}

async function saveResult() {
  await api.post(`/api/games/${props.game.id}/result`, result.value)
  emit('reload')
}

function formatDate(iso) {
  try {
    return new Date(iso).toLocaleString('de-DE', {
      dateStyle: 'medium',
      timeStyle: 'short'
    })
  } catch {
    return iso
  }
}
</script>

<style scoped>
.item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: .75rem;
  border: 1px solid #e5e7eb;
  border-radius: .75rem;
}

.main {
  display: flex;
  gap: .5rem;
  align-items: center;
  flex-wrap: wrap;
}

.votes {
  margin-left: .5rem;
  font-weight: 600;
}

.result-text {
  font-weight: 600;
}

.actions {
  display: flex;
  gap: .5rem;
  align-items: center;
}

.result-input {
  width: 90px;
  padding: .3rem;
  border-radius: .4rem;
  border: 1px solid #e5e7eb;
}

button {
  border: 1px solid #e5e7eb;
  background: #fff;
  padding: .4rem .6rem;
  border-radius: .5rem;
  cursor: pointer;
}

button:hover {
  background: #f8fafc;
}

button.ok {
  border-color: #bbf7d0;
}

button.no {
  border-color: #fecaca;
}

button.danger {
  color: #b91c1c;
  border-color: #fecaca;
}
</style>
