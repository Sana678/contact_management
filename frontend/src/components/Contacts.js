// src/components/Contacts.js
import React, { useEffect, useState } from 'react';
import API from '../api';

export default function Contacts({ token, ownerId }) {

  const [list, setList] = useState([]);

  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [emails, setEmails] = useState('');
  const [phones, setPhones] = useState('');

  async function load() {
    const result = await API.listContacts(ownerId, token);
    setList(result.content || result || []);
  }

  useEffect(() => {
    if (ownerId) load();
  }, [ownerId]);

  async function create() {
    const dto = {
      firstName,
      lastName,
      emails: emails ? emails.split(',') : [],
      phones: phones ? phones.split(',') : []
    };
    await API.createContact(ownerId, dto, token);
    await load();
  }

  async function remove(id) {
    await API.deleteContact(id, token);
    await load();
  }

  return (
    <div>
      <h3>Contacts</h3>

      <div>
        <input placeholder="First name"
               value={firstName}
               onChange={e => setFirstName(e.target.value)} />

        <input placeholder="Last name"
               value={lastName}
               onChange={e => setLastName(e.target.value)} />

        <input placeholder="emails separated by comma"
               value={emails}
               onChange={e => setEmails(e.target.value)} />

        <input placeholder="phones separated by comma"
               value={phones}
               onChange={e => setPhones(e.target.value)} />

        <button onClick={create}>Create</button>
      </div>

      <ul>
        {list.map(c => (
          <li key={c.id}>
            {c.firstName} {c.lastName}
            <button onClick={() => remove(c.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}
