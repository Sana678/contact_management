// src/components/Register.js
import React, { useState } from 'react';
import API from '../api';

export default function Register() {

  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [password, setPassword] = useState('');
  const [msg, setMsg] = useState('');

  async function submit(e) {
    e.preventDefault();
    try {
      const res = await API.register(email, phone, password);
      setMsg(JSON.stringify(res));
    } catch (ex) {
      setMsg(ex.message);
    }
  }

  return (
    <form onSubmit={submit}>
      <h3>Register</h3>

      <div>
        <input
          value={email}
          onChange={e => setEmail(e.target.value)}
          placeholder="email"
        />
      </div>

      <div>
        <input
          value={phone}
          onChange={e => setPhone(e.target.value)}
          placeholder="phone"
        />
      </div>

      <div>
        <input
          value={password}
          onChange={e => setPassword(e.target.value)}
          placeholder="password"
        />
      </div>

      <button type="submit">Register</button>

      <div>{msg}</div>
    </form>
  );
}
