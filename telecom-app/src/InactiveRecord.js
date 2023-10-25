import { useEffect, useState } from "react";
import "./InactiveRecord.css";

export default function InactiveRecords() {
  const [sims, setSims] = useState([]);

  const formatDateTime = (isoDateTime) => {
    const date = new Date(isoDateTime);

    return date.toLocaleString(); 
  };

  const fetchSims = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/number/inactivesims`
      );

      if (!response.ok) {
        throw new Error("Failed to fetch data");
      }

      const simsData = await response.json();

      console.log(simsData);

      setSims(simsData);
    } catch (error) {
      console.error("Error fetching SIMs:", error);
    }
  };
  useEffect(() => {
    fetchSims();
  }, []);

  return (
    <div className="record-table">
      <h3>Inactive Sims</h3>

      <hr />

      <table>
        <thead>
          <tr>
            <th>ID</th>

            <th>MSISDN Number</th>

            <th>Reservation Date</th>

            <th>ConnectionType</th>
          </tr>
        </thead>

        <tbody>
          {sims.map((sim) => (
            <tr key={sim.id}>
              <td>{sim.id}</td>

              <td>{sim.msisdn}</td>

              <td>{formatDateTime(sim.issuedDateTime)}</td>

              <td>{sim.connectionType}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
