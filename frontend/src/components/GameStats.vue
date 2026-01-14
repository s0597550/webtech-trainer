<template>
  <section class="stats">
    <h2>📊 Statistik</h2>

    <ul>
      <li>🏟 Spiele: {{ total }}</li>
      <li>🏆 Siege: {{ wins }}</li>
      <li>🤝 Unentschieden: {{ draws }}</li>
      <li>❌ Niederlagen: {{ losses }}</li>
    </ul>
  </section>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  games: { type: Array, required: true }
})

const total = computed(() => props.games.length)

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
  const [a, b] = result.split(':').map(Number)
  if (isNaN(a) || isNaN(b)) return null
  return { a, b }
}

function isWin(result) {
  const r = parseResult(result)
  return r && r.a > r.b
}

function isDraw(result) {
  const r = parseResult(result)
  return r && r.a === r.b
}

function isLoss(result) {
  const r = parseResult(result)
  return r && r.a < r.b
}
</script>

<style scoped>
.stats {
  margin: 2rem 0;
  padding: 1rem;
  border: 1px solid #e5e7eb;
  border-radius: .75rem;
  background: #f8fafc;
}

ul {
  list-style: none;
  padding: 0;
  margin: .5rem 0 0;
}

li {
  margin-bottom: .25rem;
  font-weight: 500;
}
</style>
