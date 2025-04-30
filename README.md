# Agenda API Gateway

Gateway API che offre un'interfaccia unificata per la gestione di contatti ed eventi.

## 📋 Panoramica

Questo microservizio implementa un gateway API che funge da punto di accesso unificato per due microservizi distinti:
- **Servizio Contatti**: gestisce le informazioni relative ai contatti personali
- **Servizio Eventi**: gestisce gli appuntamenti e gli eventi associati ai contatti

L'architettura utilizza i client REST di MicroProfile per comunicare con i microservizi sottostanti, esponendo un'API unificata per le applicazioni client.

## 🔧 Architettura

Il sistema è strutturato secondo i seguenti componenti:

```
┌─────────────┐     ┌──────────────────┐     ┌─────────────────┐
│             │     │                  │     │                 │
│  Client     │────▶│  Gateway API    │────▶│  Servizio       │
│  (Frontend) │     │                   │    │  Contatti       │
│             │     │                  │     │                 │
└─────────────┘     └──────────────────┘     └─────────────────┘
                           │
                           │
                           ▼
                    ┌─────────────────┐
                    │                 │
                    │  Servizio       │
                    │  Eventi         │
                    │                 │
                    └─────────────────┘
```

## 📦 Componenti Principali

### Modelli Dati
- **Contact**: rappresenta un contatto con attributi come nome, cognome, email e numero di telefono
- **Event**: rappresenta un evento con attributi come titolo, descrizione, date di inizio/fine, posizione e ID del contatto associato

### Client API
- **ContactClient**: interfaccia REST client per comunicare con il servizio contatti
- **EventClient**: interfaccia REST client per comunicare con il servizio eventi

### Risorse API
- **GatewayResource**: espone gli endpoint unificati per la gestione di contatti ed eventi

## 🔄 Funzionalità API

### Gestione Contatti
- Recupero di tutti i contatti
- Recupero di un contatto specifico per ID
- Ricerca contatti per termine
- Creazione di un nuovo contatto
- Aggiornamento di un contatto esistente
- Eliminazione di un contatto

### Gestione Eventi
- Recupero di tutti gli eventi
- Recupero di un evento specifico per ID
- Recupero degli eventi associati a un contatto specifico
- Recupero degli eventi imminenti
- Ricerca eventi per termine
- Creazione di un nuovo evento
- Aggiornamento di un evento esistente
- Eliminazione di un evento

### Funzionalità Composte
- Recupero dei dettagli completi di un contatto, inclusi tutti gli eventi associati

## 🔌 Endpoint API

### Endpoint Contatti
- `GET /api/contacts` - Recupera tutti i contatti
- `GET /api/contacts/{id}` - Recupera un contatto specifico per ID
- `GET /api/contacts/search?term={term}` - Cerca contatti per termine
- `POST /api/contacts` - Crea un nuovo contatto
- `PUT /api/contacts/{id}` - Aggiorna un contatto esistente
- `DELETE /api/contacts/{id}` - Elimina un contatto
- `GET /api/contacts/{id}/details` - Recupera un contatto e tutti i suoi eventi associati

### Endpoint Eventi
- `GET /api/events` - Recupera tutti gli eventi
- `GET /api/events/{id}` - Recupera un evento specifico per ID
- `GET /api/events/contact/{contactId}` - Recupera eventi associati a un contatto
- `GET /api/events/upcoming` - Recupera eventi imminenti
- `GET /api/events/search?term={term}` - Cerca eventi per termine
- `POST /api/events` - Crea un nuovo evento
- `PUT /api/events/{id}` - Aggiorna un evento esistente
- `DELETE /api/events/{id}` - Elimina un evento
