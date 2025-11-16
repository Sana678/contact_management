// src/components/Login.js
import React, { useState } from 'react';
import API from '../api';

export default function Login({ onLogin }) {

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [err, setErr] = useState(null);

  async function submit(e) {
    e.preventDefault();
    try {
      const data = await API.login(username, password);
      if (data.token) {
        onLogin(data.token);
      } else {
        setErr("Invalid login");
      }
    } catch (ex) {
      setErr(ex.message);
    }
  }

  return (
    <form onSubmit={submit}>
      <h3>Login (email or phone)</h3>

      <div>
        <input
          value={username}
          onChange={e => setUsername(e.target.value)}
          placeholder="email or phone"
        />
      </div>

      <div>
        <input
          value={password}
          onChange={e => setPassword(e.target.value)}
          placeholder="password"
          type="password"
        />
      </div>

      <button type="submit">Login</button>

      {err && <div style={{ color: 'red' }}>{err}</div>}
    </form>
  );
}
