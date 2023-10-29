import React from 'react';
import './About.css';

class About extends React.Component {
  render() {
    return (
      <div className="about-container">
        <div className="about-content">
          <h1 className="about-title">Wireless Inventory Number Management</h1>
          <p className="about-description">
            Welcome to the Wireless Inventory Number Management system, a
            cutting-edge solution for efficient and secure management of ICCID,
            MSISDN, and IMEI numbers in wireless networks.
          </p>
          <div className="mission-section">
            <h2 className="mission-title">Our Mission</h2>
            <p className="mission-description">
              At Wireless Inventory Number Management, our mission is to simplify
              the complexities of number management for wireless service
              providers. We understand the importance of seamlessly tracking,
              reserving, and allocating numbers to streamline operations. Our goal
              is to empower telecom professionals with a reliable tool that
              enhances their inventory management processes.
            </p>
          </div>

          <div className="offering-section">
            <h2 className="offering-title">What We Offer</h2>
            <p className="offering-description">
              Number Reservation: With our user-friendly platform, you can reserve ICCID, MSISDN, and
              IMEI numbers, ensuring they are readily available for your future
              needs. Allocation to Customers: We make it easy to associate ICCID
              numbers with corresponding MSISDN and IMEI, simplifying the
              allocation process. Tracking and Availability: Keep a close eye on
              reservation dates and monitor the availability of numbers. Our
              system provides the insights you need to make informed allocation
              decisions.
            </p>
          </div>

          <div className="revolution-section">
            <h2 className="revolution-title">Join the 5G Revolution with Us</h2>
            <p className="revolution-description">
              Wireless Inventory Number Management is here to empower telecom
              professionals, businesses, and service providers with the tools and
              resources they need to excel in the 5G era. We take the complexities
              out of number management, allowing you to focus on innovation and
              growth. This is your gateway to the future of telecommunications.
              Explore our system, experience the power of efficient number
              management, and become a part of the 5G revolution.
            </p>
          </div>
        </div>
      </div>
    );
  }
}
export default About;
