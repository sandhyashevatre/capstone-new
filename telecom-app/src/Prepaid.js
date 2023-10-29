import React, { useState } from "react";
import "./Prepaid.css";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function Prepaid() {
  const images = ["/images/s2.png"];
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
      })
    );
    try {
      const response = await fetch("http://localhost:8080/api/reservation", {
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
          connectionType: "prepaid",
        }),
      });
      if (response.ok) {
        toast.success("Sim has been registered successfully", {
          position: "top-right",
          autoClose: 5000, // Auto close the notification after 5 seconds
        });
        // Reload the page after a short delay (5 seconds)
        setTimeout(() => {
          window.location.reload();
        }, 5000);
        console.log(await response.json());
      } else {
        console.error("Failed to fetch data.");
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };
  return (
    <div className="SIMPro">
      <img src={images} alt="s2" />
      <div className="prepaid-container">
        <form method="POST" className="reservation-form" onSubmit={onsubmit}>
          <h2>{`Prepaid`}</h2>
          <label htmlFor="customer" className="form-label">
            Customer Name
          </label>
          <input
            type="text"
            value={customer}
            name="customer"
            onChange={changecustomerhandle}
            className="form-input"
          />
          <label htmlFor="phone-number" className="form-label">
            Mobile Number
          </label>
          <input
            type="text"
            value={phoneNumber}
            name="phone-number"
            onChange={changephonenumberhandle}
            className="form-input"
          />
          <label htmlFor="provider" className="form-label radio-label">
            Select SIM
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
            Address
            <input
              type="text"
              value={location}
              name="location"
              onChange={changelocationhandle}
              className="form-input"
            />
          </label>
          <button type="submit" className="button">
            Submit
          </button>
        </form>
      </div>
    </div>
  );
}
