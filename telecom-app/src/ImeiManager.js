import { useState } from "react";
import React from 'react';
import "./ImeiManager.css"
import { toast } from "react-toastify";

export default function ImeiManager() {
  const [ImeiNumber, setImeiNumber] = useState("");

  const [PhoneNumber, setPhoneNumber] = useState("");

  const changeimeinumber = (event) => {
    setImeiNumber(event.target.value);
  };

  const changephonenumber = (event) => {
    setPhoneNumber(event.target.value);
  };

  const onsubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await fetch(
        "http://localhost:8080/api/insertSim",
        {
          method: "POST",

          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
            "Content-Type": "application/json",
          },

          body: JSON.stringify({
            imei: ImeiNumber,

            msisdn: PhoneNumber,
          }),
        }
      );

      if (response.ok) {
        const b = await response.json();

        if (b === true) {
          toast.success("Imei Registered Successfully", {
            position: "top-center",

            autoClose: 5000,
          });
        } else {
          toast.error("This IMEI is already in use", {
            position: "top-center",

            autoClose: 5000,
          });
        }


        setTimeout(() => {
          window.location.reload();
        }, 5000);
      } else {
        console.error("Failed to fetch data.");
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <>
      <div className="imei-container">
        <form method="POST" className="imei-form" onSubmit={onsubmit}>
          <label htmlFor="IMEINumber" className="form-label">
            IMEI Number
          </label>

          <input
            type="text"
            name="IMEINumber"
            value={ImeiNumber}
            onChange={changeimeinumber}
            className="form-input"
          />

          <label htmlFor="PhoneNumber" className="form-label">
            Phone Number
          </label>

          <input
            type="text"
            name="PhoneNumber"
            value={PhoneNumber}
            onChange={changephonenumber}
            className="form-input"
          />

          <button type="submit" className="button">
            Submit
          </button>
        </form>
      </div>
    </>
  );
}
