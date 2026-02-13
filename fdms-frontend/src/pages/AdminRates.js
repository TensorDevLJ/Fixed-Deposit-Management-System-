import React, { useState } from "react";
import API from "../api";

function AdminRates() {
  const [form, setForm] = useState({
    minTenureMonths: "",
    maxTenureMonths: "",
    interestRate: "",
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    try {
      const res = await API.post("/admin/rates", form);
      alert("Rate added");
    } catch (err) {
      alert("Error adding rate");
    }
  };

  return (
    <div>
      <h2>Admin: Set Interest Rate</h2>
      <input
        name="minTenureMonths"
        placeholder="Min Tenure"
        onChange={handleChange}
      />
      <input
        name="maxTenureMonths"
        placeholder="Max Tenure"
        onChange={handleChange}
      />
      <input
        name="interestRate"
        placeholder="Interest Rate"
        onChange={handleChange}
      />
      <button onClick={handleSubmit}>Add Rate</button>
    </div>
  );
}

export default AdminRates;
