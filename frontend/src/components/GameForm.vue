<template>
  <form class="card" @submit.prevent="submit">
    <h2>Neues Spiel</h2>

    <label>Gegner
      <input v-model.trim="form.opponent" placeholder="z.B. FC Beispiel" required />
    </label>

    <label>Datum & Uhrzeit
      <input type="datetime-local" v-model="form.dateTimeLocal" required />
    </label>

    <label>Ort
      <input v-model.trim="form.location" placeholder="z.B. Stadion Nord" required />
    </label>

    <div class="row">
      <button type="submit">Anlegen</button>
    </div>
  </form>
</template>

<script setup>
import { reactive } from 'vue'
import { api } from '../services/api.js'

const emit = defineEmits(['saved'])


// Das Formularmodell
const form = reactive({
  opponent: '',
  dateTimeLocal: '',
  location: ''
})

// Hilfsfunktion: wandelt datetime-local in ISO um, wie Backend erwartet
function toIso(localStr) {
  return localStr.length === 16 ? `${localStr}:00` : localStr
}

// Formular zurücksetzen
function reset() {
  form.opponent = ''
  form.dateTimeLocal = ''
  form.location = ''
}

// Speichern
async function submit() {
  const payload = {
    opponent: form.opponent,
    dateTime: toIso(form.dateTimeLocal),
    location: form.location
  }

  try {
    // ✅ EINZIGE ÄNDERUNG: korrekter API-Pfad
    await api.post('/api/games', payload)

    emit('saved')
    alert("Spiel gespeichert!")
    reset()
  } catch (e) {
    console.error(e)
    alert("Speichern fehlgeschlagen")
  }
}
</script>

<style scoped>
.card {
  border: 1px solid #e5e7eb;
  border-radius: .75rem;
  padding: 1rem;
}
label {
  display: grid;
  gap: .35rem;
  margin-bottom: .75rem;
}
input {
  padding: .5rem .6rem;
  border: 1px solid #e5e7eb;
  border-radius: .5rem;
}
.row {
  display: flex;
  gap: .5rem;
}
button {
  border: 1px solid #e5e7eb;
  background: #fff;
  padding: .5rem .8rem;
  border-radius: .5rem;
  cursor: pointer;
}
button:hover {
  background: #f8fafc;
}
</style>
