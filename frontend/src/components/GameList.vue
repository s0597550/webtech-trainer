<template>
  <section>
    <header class="list-header">
      <h2>Spiele</h2>

      <small v-if="loading">lädt…</small>

      <small v-else>
        {{ games.length }} Spiele |
        🏆 {{ wins }} |
        🤝 {{ draws }} |
        ❌ {{ losses }}
      </small>
    </header>

    <ul class="game-list">
      <GameItem
          v-for="g in orderedGames"
          :key="g.id"
          :game="g"
          @edit="$emit('edit', g)"
          @remove="$emit('remove', g.id)"
          @reload="emitReload"
      />
    </ul>

    <p v-if="!loading && games.length === 0" class="empty">
      Noch keine Spiele eingetragen.
    </p>
  </section>
</template>

<script setup>
import { computed } from 'vue'
import GameItem from './GameItem.vue'

const emit = defineEmits(['edit', 'remove', 'reload'])

const props = defineProps({
  games: { type: Array, required: true },
  loading: { type: Boolean, default: false }
})

/* =========================
   BESTEHEND
========================= */
const orderedGames = computed(() =>
    [...props.games].sort((a, b) => new Date(a.kickoff) - new Date(b.kickoff))
)

function emitReload() {
  emit('reload')
}

/* =========================
   NEU: STATISTIK
========================= */
const wins = computed(() =>
    props.games.filter(g => isWin(g.result)).length
)

const draws = computed(() =>
    props.games.filter(g => isDraw(g.result)).length
)

const losses = computed(() =>
    props.games.filter(g => isLoss(g.result)).length
)

function parseResult(result) {
  if (!result || !result.includes(':')) return null
  const [home, away] = result.split(':').map(Number)
  if (isNaN(home) || isNaN(away)) return null
  return { home, away }
}

function isWin(result) {
  const r = parseResult(result)
  return r && r.home > r.away
}

function isDraw(result) {
  const r = parseResult(result)
  return r && r.home === r.away
}

function isLoss(result) {
  const r = parseResult(result)
  return r && r.home < r.away
}
</script>

<style scoped>
.list-header {
  display: flex;
  align-items: baseline;
  gap: .75rem;
}

.game-list {
  list-style: none;
  padding: 0;
  margin: .5rem 0 0;
  display: grid;
  gap: .5rem;
}

.empty {
  opacity: .7;
  margin-top: .5rem;
}
</style>
