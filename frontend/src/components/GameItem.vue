<template>
  <li class="item">
    <div class="main">
      <strong>{{ game.opponent }}</strong>
      <span>•</span>
      <time :datetime="game.kickoff">
        {{ formatDate(game.kickoff || game.dateTime) }}
      </time>
      <span>•</span>
      <span>{{ game.location }}</span>

      <span class="votes">
        👍 {{ game.likes }} | 👎 {{ game.dislikes }}
      </span>

      <span v-if="game.result" class="result-text">
        🏁 {{ game.result }}
      </span>
    </div>

    <div class="actions">
      <button class="ok" @click="likeGame">👍</button>
      <button class="no" @click="dislikeGame">👎</button>

      <input
          v-model="result"
          placeholder="Ergebnis z.B. 2:1"
          class="result-input"
      />
      <button @click="saveResult">Speichern</button>

      <button @click="$emit('edit', game)">Bearbeiten</button>
      <button class="danger" @click="$emit('remove')">Löschen</button>
    </div>
  </li>
</template>

<script setup>
import { ref, watch } from 'vue'   // ⬅️ NUR ERGÄNZT
import { api } from '../services/api.js'

const props = defineProps({
  game: { type: Object, required: true }
})

const emit = defineEmits(['reload'])

const result = ref(props.game.result || '')

// ⬅️ NUR DAS HIER IST NEU
watch(
    () => props.game.result,
    (newResult) => {
      result.value = newResult || ''
    }
)

async function likeGame() {
  await api.post(`/api/games/${props.game.id}/like`, null)
  emit('reload')
}

async function dislikeGame() {
  await api.post(`/api/games/${props.game.id}/dislike`, null)
  emit('reload')
}

async function saveResult() {
  await api.put(`/api/games/${props.game.id}/result`, result.value)
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
/* unverändert */
</style>
