import React, { useEffect, useState } from "react";
import API from "../api";

function Analytics() {
  const [data, setData] = useState(null);

  useEffect(() => {
    API.get("/analytics/summary")
      .then((res) => setData(res.data))
      .catch(() => alert("Error loading analytics"));
  }, []);

  return (
    <div>
      <h2>Analytics</h2>
      {data ? (
        <pre>{JSON.stringify(data, null, 2)}</pre>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
}

export default Analytics;
