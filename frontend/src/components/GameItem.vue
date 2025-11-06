<template>
  <li class="item">
    <div class="main">
      <strong>{{ game.opponent }}</strong>
      <span>•</span>
      <time :datetime="game.kickoff">{{ formatDate(game.kickoff) }}</time>
      <span>•</span>
      <span>{{ game.location }}</span>
    </div>
    <div class="actions">
      <button @click="$emit('edit', game)">Bearbeiten</button>
      <button class="danger" @click="$emit('remove')">Löschen</button>
    </div>
  </li>
</template>

<script setup>
defineProps({ game: { type: Object, required: true } })

function formatDate(iso) {
  try {
    return new Date(iso).toLocaleString('de-DE', { dateStyle: 'medium', timeStyle: 'short' })
  } catch { return iso }
}
</script>

<style scoped>
.item { display: flex; justify-content: space-between; align-items: center; padding: .75rem; border: 1px solid #e5e7eb; border-radius: .75rem; }
.actions { display: flex; gap: .5rem; }
button { border: 1px solid #e5e7eb; background: #fff; padding: .4rem .6rem; border-radius: .5rem; cursor: pointer; }
button:hover { background: #f8fafc; }
button.danger { color: #b91c1c; border-color: #fecaca; }
</style>
