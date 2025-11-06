<template>
  <section>
    <header class="list-header">
      <h2>Spiele</h2>
      <small v-if="loading">lädt…</small>
      <small v-else>{{ games.length }} Einträge</small>
    </header>

    <ul class="game-list">
      <GameItem
          v-for="g in orderedGames"
          :key="g.id"
          :game="g"
          @edit="$emit('edit', g)"
          @remove="$emit('remove', g.id)"
      />
    </ul>

    <p v-if="!loading && games.length === 0" class="empty">Noch keine Spiele eingetragen.</p>
  </section>
</template>

<script setup>
import { computed } from 'vue'
import GameItem from './GameItem.vue'

const props = defineProps({
  games: { type: Array, required: true },
  loading: { type: Boolean, default: false }
})

const orderedGames = computed(() =>
    [...props.games].sort((a, b) => new Date(a.kickoff) - new Date(b.kickoff))
)
</script>

<style scoped>
.list-header { display: flex; align-items: baseline; gap: .75rem; }
.game-list { list-style: none; padding: 0; margin: .5rem 0 0; display: grid; gap: .5rem; }
.empty { opacity: .7; margin-top: .5rem; }
</style>
