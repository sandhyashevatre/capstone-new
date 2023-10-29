import { useEffect, useState } from "react";

import "./ReservRecord.css"; 

export default function ReserRecords() {
  const [sims, setSims] = useState([]);

  const formatDateTime = (isoDateTime) => {
    const date = new Date(isoDateTime);

    return date.toLocaleString();
  };

  const fetchSims = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/allreservedsims`,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
            "Content-Type": "application/json",
          },
        }
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

            <th>Customer Name</th>

            <th>Reservation Date</th>
          </tr>
        </thead>

        <tbody>
          {sims.map((sim) => (
            <tr key={sim.id}>
              <td>{sim.id}</td>

              <td>{sim.msisdn}</td>

              <td>{sim.customerName}</td>

              <td>{sim.reservationDateTime}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
