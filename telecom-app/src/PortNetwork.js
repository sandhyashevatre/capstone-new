import { useState } from "react";
import { toast } from "react-toastify";
import "./PortNetwork.css";
import React from "react";

export default function PortNetwork(props) {
  const images = ["/images/port0.png"];
  const [phoneNumber, setPhoneNumber] = useState("");

  const [customer, setCustomer] = useState("");

  const [provider, setProvider] = useState("");

  const changephonenumberhandle = (event) => {
    setPhoneNumber(event.target.value);
  };

  const changeproviderhandle = (event) => {
    setProvider(event.target.value);
  };

  const changecustomerhandle = (event) => {
    setCustomer(event.target.value);
  };

  const onsubmit = async (event) => {
    event.preventDefault();

    console.log(
      JSON.stringify({
        customerNumber: customer,

        reservingNumber: phoneNumber,

        provider: provider,

        location: "",

        connectionType: props.connection.type,
      })
    );

    try {
      const response = await fetch("http://localhost:8080/api/changeProvider", {
        method: "POST",

        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "Content-Type": "application/json",
        },

        body: JSON.stringify({
          customerName: customer,

          reservingNumber: phoneNumber,

          provider: provider,

          location: "",

          connectionType: props.connection.type,
        }),
      });

      if (response.ok) {
        const data = await response.json();

        if (data === true) {
          toast.success("Sim Ported successfully", {
            position: "top-center",

            autoClose: 5000,
          });
        } else {
          toast.error("Sim Porting Failed", {
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
      <div className="SIMPort">
        <img src={images} alt="port0" />
        <div className="provider-container">
          <form method="POST" className="provider-form" onSubmit={onsubmit}>
            <h3>Port Your Sim</h3>

            <hr />

            <label htmlFor="customer" className="form-label">
              Customer Name
            </label>

            <input
              type="text"
              value={customer}
              id="customer"
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
              id="phone-number"
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
                id="airtel"
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

            <button type="submit" className="button">
              Submit
            </button>
          </form>
        </div>
      </div>
    </>
  );
}
