import { useEffect, useState } from "react";
import React from 'react';


import "./Data.css"; 

export default function Data(props) {
  const [sims, setSims] = useState([]);


  useEffect(() => {
    const fetchSims = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/all${props.connection.type}sims`,
          {
            method: "GET",
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
    fetchSims();
  }, [props.connection.type]);

  return (
    <div className="Data-table">
      <table>
        <thead>
          <tr>
            <th>ID</th>

            <th>MSISDN Number</th>

            <th>ICCID</th>

            <th>IMEI Number</th>

            <th>Reservation Date</th>

            <th>Activity Status</th>
          </tr>
        </thead>

        <tbody>
          {sims.map((sim) => (
            <tr key={sim.id}>
              <td>{sim.id}</td>

              <td>{sim.msisdn}</td>

              <td>{sim.iccid}</td>

              <td>{sim.imei ?? "N/A"}</td>

              <td>{sim.reservationDateTime}</td>

              <td>
                <button className={sim.activated ? "active" : "inactive"}>
                  {sim.activated ? "Active" : "Inactive"}
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
