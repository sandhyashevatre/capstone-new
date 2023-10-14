import { useState } from "react";

export default function Prepaid() {
  const [phoneNumber, setPhoneNumber] = useState("");
  const [location, setLocation] = useState("");
  const [customer, setCustomer] = useState("");
  const [provider,setProvider] = useState("");
  const changephonenumberhandle = (event) => {
    setPhoneNumber(event.target.value);
  };
  const changeproviderhandle = (event) => {
     setProvider(event.target.value);
  }
  const changelocationhandle = (event) => {
    setLocation(event.target.value);
  };
  const changecustomerhandle = (event) => {
    setCustomer(event.target.value);
  };
  const onsubmit = async (event) => {
    event.preventDefault();
    console.log(JSON.stringify({
      customerNumber: customer,
      phoneNumber: phoneNumber,
      provider: provider,
      location: location,
    }));
    try {
      const response = await fetch("http://localhost:8080/api/reservation", {
        method: "POST",
        headers: {
          Authorization : `Bearer ${localStorage.getItem('token')}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          customerName: customer,
          phoneNumber: phoneNumber,
          provider: provider,
          location: location,
        }),
      });
      if (response.ok) {
        
        console.log(await response.json());
      } else {
        console.error("Failed to fetch data.");
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };
  return (
    <div>
      <form method="POST" className="login-form" onSubmit={onsubmit}>
        <label htmlFor="customer">Customer</label>
        <input
          type="text"
          value={customer}
          name="customer"
          onChange={changecustomerhandle}
        /> 
        <label htmlFor="phone-number">Phone Number</label>
        <input
          type="text"
          value={phoneNumber}
          name="phone-number"
          onChange={changephonenumberhandle}
        />
        <label htmlFor="provider">Networks</label>
        <input type="radio" name="provider" value="AIRTEL" onChange={changeproviderhandle} />
        <input type="radio" name="provider" value="JIO" onChange={changeproviderhandle} />
        <input type="radio" name="provider" value="VI" onChange={changeproviderhandle} />
        <input type="radio" name="provider" value="AIRCEL" onChange={changeproviderhandle} />
        <label htmlFor="location">Location</label>
        <input
          type="text"
          value={location}
          name="location"
          onChange={changelocationhandle}
        />
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}
