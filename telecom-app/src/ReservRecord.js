import { useEffect, useState } from "react";

import "./ReservRecord.css"; // Import the CSS file

export default function ReserRecords() {
  const [sims, setSims] = useState([]);

  const formatDateTime = (isoDateTime) => {
    const date = new Date(isoDateTime);

    return date.toLocaleString(); 
  };

  const fetchSims = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/number/allreservations`
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
      <h3>Reserved Record</h3>

      <hr />

      <table>
        <thead>
          <tr>
            <th>ID</th>

            <th>MSISDN Number</th>

            <th>provider</th>

            <th>Reservation Date</th>

            <th>ConnectionType</th>
          </tr>
        </thead>

        <tbody>
          {sims.map((sim) => (
            <tr key={sim.id}>
              <td>{sim.id}</td>

              <td>{sim.phoneNumber}</td>

              <td>
                {sim.provider}
              </td>

              <td>{formatDateTime(sim.reservationDateTime)}</td>

              <td>{sim.connectionType}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
