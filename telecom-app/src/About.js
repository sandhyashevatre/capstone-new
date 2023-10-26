import { Component } from "react";
import "./About.css";
import React from 'react';


class Contact extends Component {
  
  render() {
    return (
      <>
        {/* Footer */}
        <div className="About">
          <div className="Introduction">
            <h1>About Wireless Inventory Number Management</h1>
            Welcome to the Wireless Inventory Number Management system, a
            cutting-edge solution for efficient and secure management of ICCID,
            MSISDN, and IMEI numbers in wireless networks.
            <h2>Our Mission</h2>
            At Wireless Inventory Number Management, our mission is to simplify
            the complexities of number management for wireless service
            providers. We understand the importance of seamlessly tracking,
            reserving, and allocating numbers to streamline operations. Our goal
            is to empower telecom professionals with a reliable tool that
            enhances their inventory management processes.
            <h2>What We Offer Number Reservation:</h2>
            With our user-friendly platform, you can reserve ICCID, MSISDN, and
            IMEI numbers, ensuring they are readily available for your future
            needs. Allocation to Customers: We make it easy to associate ICCID
            numbers with corresponding MSISDN and IMEI, simplifying the
            allocation process. Tracking and Availability: Keep a close eye on
            reservation dates and monitor the availability of numbers. Our
            system provides the insights you need to make informed allocation
            decisions.
            <h2>Join the 5G Revolution with Us</h2>
            Wireless Inventory Number Management is here to empower telecom
            professionals, businesses, and service providers with the tools and
            resources they need to excel in the 5G era. We take the complexities
            out of number management, allowing you to focus on innovation and
            growth. This is your gateway to the future of telecommunications.
            Explore our system, experience the power of efficient number
            management, and become a part of the 5G revolution.
          </div>
        </div>
      </>
    );
  }
}
export default Contact;
