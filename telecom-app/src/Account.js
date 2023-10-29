import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Account.css'; 

export default function Account() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
  
    const history = useNavigate();
    const onsubmit = async (event) => {
      event.preventDefault();
  
      try {
        const response = await fetch("http://localhost:8080/api/auth/token", {
          method: "POST",
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            username: username,
            password: password,
          }),
        });
  
        if (response.ok) {
          const data = await response.json();
          localStorage.setItem('token', data.token);
          localStorage.setItem('username',username);  
          history('/home')
        } else {
          console.error("Failed to fetch data.");
        }
      } catch (error) {
        console.error("Error:", error);
      }
    }
  
    const handleUserChange = (event) => {
      setUsername(event.target.value);
    };
  
    const handlePassChange = (event) => {
      setPassword(event.target.value);
    };
  
    return (
      <div className="account-container">
        <form className="login-form" method="POST" onSubmit={onsubmit}>
          <label htmlFor="username">Username</label>
          <input type="text" value={username} name="username" onChange={handleUserChange} />
          <label htmlFor="password">Password</label>
          <input type="password" value={password} name="password" onChange={handlePassChange} />
          <button type="submit">Submit</button>
        </form>
      </div>
    );
}
