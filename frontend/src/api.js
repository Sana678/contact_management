// src/api.js
const API = {

  login: async (username, password) => {
    const res = await fetch('/api/auth/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password })
    });
    return res.json();
  },

  register: async (email, phone, password) => {
    const res = await fetch('/api/auth/register', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, phone, password })
    });
    return res.json();
  },

  listContacts: async (ownerId, token) => {
    const res = await fetch(`/api/contacts?ownerId=${ownerId}`, {
      headers: { 'Authorization': `Bearer ${token}` }
    });
    return res.json();
  },

  createContact: async (ownerId, dto, token) => {
    const res = await fetch(`/api/contacts?ownerId=${ownerId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
      body: JSON.stringify(dto)
    });
    return res.json();
  },

  deleteContact: async (id, token) => {
    const res = await fetch(`/api/contacts/${id}`, {
      method: 'DELETE',
      headers: { 'Authorization': `Bearer ${token}` }
    });
    return res.json();
  }

};

export default API;
