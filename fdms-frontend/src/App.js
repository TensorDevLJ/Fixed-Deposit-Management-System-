import React from "react";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import Register from "./pages/Register";
import CreateFD from "./pages/CreateFD";
import AdminRates from "./pages/AdminRates";
import Analytics from "./pages/Analytics";

function App() {
  return (
    <BrowserRouter>
      <nav style={{ padding: 10 }}>
        <Link to="/">Register</Link> |{" "}
        <Link to="/fd">Create FD</Link> |{" "}
        <Link to="/admin">Admin Rates</Link> |{" "}
        <Link to="/analytics">Analytics</Link>
      </nav>

      <Routes>
        <Route path="/" element={<Register />} />
        <Route path="/fd" element={<CreateFD />} />
        <Route path="/admin" element={<AdminRates />} />
        <Route path="/analytics" element={<Analytics />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
