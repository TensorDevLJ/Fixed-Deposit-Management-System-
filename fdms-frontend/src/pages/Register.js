import React, { useState } from "react";
import API from "../api";

function Register() {
  const [form, setForm] = useState({
    username: "",
    password: "",
    fullName: "",
    email: "",
    role: "USER",
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    try {
      const res = await API.post("/users/register", form);
      alert("User registered: " + JSON.stringify(res.data));
    } catch (err) {
      alert("Error registering user");
    }
  };

  return (
    <div>
      <h2>User Registration</h2>
      <input name="username" placeholder="Username" onChange={handleChange} />
      <input name="password" placeholder="Password" onChange={handleChange} />
      <input name="fullName" placeholder="Full Name" onChange={handleChange} />
      <input name="email" placeholder="Email" onChange={handleChange} />
      <button onClick={handleSubmit}>Register</button>
    </div>
  );
}

export default Register;
