import React, { useState } from "react";
import API from "../api";

function CreateFD() {
  const [userId, setUserId] = useState("");
  const [principalAmount, setPrincipalAmount] = useState("");
  const [tenureMonths, setTenureMonths] = useState("");

  const handleSubmit = async () => {
    try {
      const res = await API.post(`/fds/${userId}`, {
        principalAmount,
        tenureMonths,
      });
      alert("FD Created: " + JSON.stringify(res.data));
    } catch (err) {
      alert("Error creating FD");
    }
  };

  return (
    <div>
      <h2>Create Fixed Deposit</h2>
      <input
        placeholder="User ID"
        onChange={(e) => setUserId(e.target.value)}
      />
      <input
        placeholder="Principal Amount"
        onChange={(e) => setPrincipalAmount(e.target.value)}
      />
      <input
        placeholder="Tenure (months)"
        onChange={(e) => setTenureMonths(e.target.value)}
      />
      <button onClick={handleSubmit}>Create FD</button>
    </div>
  );
}

export default CreateFD;
