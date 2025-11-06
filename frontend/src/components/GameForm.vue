<template>
  <form class="card" @submit.prevent="submit">
    <h2>{{ isEdit ? 'Spiel bearbeiten' : 'Neues Spiel' }}</h2>

    <label>Gegner
      <input v-model.trim="form.opponent" placeholder="z.B. FC Beispiel" required />
    </label>

    <label>Datum & Uhrzeit
      <input type="datetime-local" v-model="form.kickoffLocal" required />
    </label>

    <label>Ort
      <input v-model.trim="form.location" placeholder="z.B. Stadion Nord" required />
    </label>

    <div class="row">
      <button type="submit">{{ isEdit ? 'Speichern' : 'Anlegen' }}</button>
      <button type="button" v-if="isEdit" @click="$emit('cancel')">Abbrechen</button>
    </div>
  </form>
</template>

<script setup>
import { computed, reactive, watch } from 'vue'
import { api } from '../services/api'

const props = defineProps({ initial: { type: Object, default: null } })
const emit = defineEmits(['saved', 'cancel'])

const form = reactive({ id: null, opponent: '', kickoffLocal: '', location: '' })
const isEdit = computed(() => !!form.id)

watch(() => props.initial, (val) => {
  if (val) {
    form.id = val.id
    form.opponent = val.opponent || ''
    form.location = val.location || ''
    const d = val.kickoff ? new Date(val.kickoff) : new Date()
    form.kickoffLocal = toLocalInputValue(d)
  } else {
    reset()
  }
}, { immediate: true })

function toLocalInputValue(date) {
  const pad = n => String(n).padStart(2, '0')
  const yyyy = date.getFullYear()
  const mm = pad(date.getMonth() + 1)
  const dd = pad(date.getDate())
  const hh = pad(date.getHours())
  const mi = pad(date.getMinutes())
  return `${yyyy}-${mm}-${dd}T${hh}:${mi}`
}

function toIsoFromLocal(localStr) {
  const d = new Date(localStr)
  return d.toISOString()
}

function reset() {
  form.id = null
  form.opponent = ''
  form.kickoffLocal = ''
  form.location = ''
}

async function submit() {
  const payload = {
    opponent: form.opponent,
    kickoff: toIsoFromLocal(form.kickoffLocal),
    location: form.location
  }
  try {
    let res
    if (form.id) {
      res = await api.put(`/games/${form.id}`, payload)
    } else {
      res = await api.post('/games', payload)
    }
    emit('saved', res.data)
    if (!form.id) reset()
  } catch (e) {
    console.error(e)
    alert('Speichern fehlgeschlagen')
  }
}
</script>

<style scoped>
.card { border: 1px solid #e5e7eb; border-radius: .75rem; padding: 1rem; }
label { display: grid; gap: .35rem; margin-bottom: .75rem; }
input { padding: .5rem .6rem; border: 1px solid #e5e7eb; border-radius: .5rem; }
.row { display: flex; gap: .5rem; }
button { border: 1px solid #e5e7eb; background: #fff; padding: .5rem .8rem; border-radius: .5rem; cursor: pointer; }
button:hover { background: #f8fafc; }
</style>
