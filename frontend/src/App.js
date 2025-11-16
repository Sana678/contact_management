// src/App.js
import React, { useState } from 'react';
import Login from './components/Login';
import Register from './components/Register';
import Contacts from './components/Contacts';

function App() {

  const [token, setToken] = useState(localStorage.getItem('token'));
  const [ownerId, setOwnerId] = useState(localStorage.getItem('ownerId'));

  function onLogin(t) {
    localStorage.setItem('token', t);
    setToken(t);
  }

  return (
    <div style={{ padding: 20 }}>

      {!token ? (
        <>
          <Login onLogin={onLogin} />
          <Register />
        </>
      ) : (
        <>
          <p><b>Token found.</b></p>
          <p>Enter ownerId (your user ID):</p>

          <input
            value={ownerId || ''}
            onChange={e => {
              setOwnerId(e.target.value);
              localStorage.setItem('ownerId', e.target.value);
            }}
            placeholder="User ID"
          />

          <Contacts token={token} ownerId={ownerId} />
        </>
      )}

    </div>
  );
}

export default App;
