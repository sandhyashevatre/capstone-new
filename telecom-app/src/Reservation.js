import { useState } from "react";
import "./Reservation.css";
import { toast } from "react-toastify";
import React from 'react';


export default function Reservation(props) {
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
    const onsubmit = async (e) =>{
        e.preventDefault();
        try{
        const response = await fetch('http://localhost:8080/api/reserve',{
            method : "POST",
            headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
            "Content-Type": "application/json",
          },
            body : JSON.stringify({
                customerName : customer,
                provider : provider,
                reservingNumber : phoneNumber,
                connectionType : props.connection.type,
            }),
        });
        if (response.ok) {
            const data = await response.json();
    
            if (data === true) {
              toast.success("Sim Reserved successfully", {
                position: "top-center",
    
                autoClose: 5000,
              });
            } else {
              toast.error("Sim Reservation Failed", {
                position: "top-center",
    
                autoClose: 5000,
              });
            }
        }
        }catch(e){
            console.error(e);
        }
    }
  return (
    <div className="reservation-container">
      <form method="POST" className="reservation-form" onSubmit={onsubmit}>
        {/* <h2>{`${props.connection.type}`}</h2> */}

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
  );
}
