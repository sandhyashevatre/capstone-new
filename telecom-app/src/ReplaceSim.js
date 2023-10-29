import { useState } from "react";
import { toast } from "react-toastify";
import React from "react";

import "./ReplaceSim.css";

export default function ReplaceSim(props) {
  const [phoneNumber, setPhoneNumber] = useState("");

  const [location, setLocation] = useState("");

  const [customer, setCustomer] = useState("");

  const [provider, setProvider] = useState("");

  const changephonenumberhandle = (event) => {
    setPhoneNumber(event.target.value);
  };

  const changeproviderhandle = (event) => {
    setProvider(event.target.value);
  };

  const changelocationhandle = (event) => {
    setLocation(event.target.value);
  };

  const changecustomerhandle = (event) => {
    setCustomer(event.target.value);
  };

  const onsubmit = async (event) => {
    event.preventDefault();

    console.log(
      JSON.stringify({
        customerNumber: customer,

        phoneNumber: phoneNumber,

        provider: provider,

        location: location,

        connectionType: props.connection.type,
      })
    );

    try {
      const response = await fetch("http://localhost:8080/api/replacesim", {
        method: "POST",

        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "Content-Type": "application/json",
        },

        body: JSON.stringify({
          customerName: customer,

          reservingNumber: phoneNumber,

          provider: provider,

          location: location,

          connectionType: props.connection.type,
        }),
      });

      if (response.ok) {
        toast.success("Sim has been registered successfully", {
          position: "top-center",
          autoClose: 5000,
        });

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
    <div className="replace-container">
      <form method="POST" className="replace-form" onSubmit={onsubmit}>
        <label htmlFor="customer" className="form-label">
          Customer
        </label>

        <input
          type="text"
          value={customer}
          name="customer"
          onChange={changecustomerhandle}
          className="form-input"
        />

        <label htmlFor="phone-number" className="form-label">
          Phone Number
        </label>
        <input
          type="text"
          value={phoneNumber}
          name="phone-number"
          onChange={changephonenumberhandle}
          className="form-input"
        />

        <label htmlFor="provider" className="form-label radio-label">
          Networks
        </label>

        <label className="radio-label">
          <input
            type="radio"
            name="provider"
            value="AIRTEL"
            onChange={changeproviderhandle}
          />
          Airtel
        </label>

        <label className="radio-label">
          <input
            type="radio"
            name="provider"
            value="JIO"
            onChange={changeproviderhandle}
          />
          Jio
        </label>

        <label className="radio-label">
          <input
            type="radio"
            name="provider"
            value="VI"
            onChange={changeproviderhandle}
          />
          Vodafone Idea
        </label>

        <label className="radio-label">
          <input
            type="radio"
            name="provider"
            value="AIRCEL"
            onChange={changeproviderhandle}
          />
          Aircel
        </label>

        <label htmlFor="location" className="form-label">
          Location
        </label>

        <input
          type="text"
          value={location}
          name="location"
          onChange={changelocationhandle}
          className="form-input"
        />

        <button type="submit" className="button">
          Submit
        </button>
      </form>
    </div>
  );
}
