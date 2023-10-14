## **CAPSTONE PROJECT**

# **Wireless Inventory Number Management**

(Software Requirements Specification)

**9th-Oct-2023**

**Submitted to: Aruvi**    


**Submitted by: Sandhya Shevatre**


**Prodapt  University, Hyderabad**


**Table of Contents**


|1|Introduction|
| :- | :- |
||<p>1\.1. Purpose</p><p>1\.2. Scope</p>|
|2|System Description|
||<p>2\.1. System Overview</p><p>2\.2. System Architecture</p><p>2\.3. Data Flow Diagram</p><p>2\.4. Technology Stack</p>|
|3|Functional Requirements|
||<p>3\.1. Use Case 1</p><p>3\.2. Use Case 2</p>|
|4|Non-Functional Requirements|
||<p>4\.1. Performance Requirements</p><p>4\.2. Security Requirements</p><p>4\.3. User Interface Requirements</p><p>4\.4. Database Requirements</p><p>4\.5. Compatibility Requirements</p>|
|5|User Interface Design|
||<p>5\.1. Wireframes</p><p>5\.2. Mockups</p>|
|6|Data Model|
||<p>6\.1. Entity-Relationship Diagram</p><p>6\.2. Database Schema</p><p></p>|
|7|API Specification|
||<p>7\.1. API Endpoints</p><p>7\.2. Data Formats</p><p>7\.3. Authentication and Authorization</p>|
|8|Security|
||<p>8\.1. Authentication</p><p>8\.2. Authorization</p><p>8\.3. Data Encryption</p>|
|9|Testing|
||<p>9\.1. Unit Testing</p><p>9\.2. Integration Testing</p><p>9\.3. User Acceptance Testing</p>|
|10|Deployment|
||<p>10\.1. Deployment Architecture</p><p>10\.2. Deployment Instruction</p>|


 ## **1. Introduction**

**1.1 Purpose**

The purpose of this document is to outline the requirements for the development of the Wireless Inventory Number Management system. It focuses on defining both the functional and non-functional aspects necessary for successful system implementation.

**1.2 Scope**

The system is designed to manage ICCID, MSISDN, and IMEI numbers in wireless networks. This encompasses the tracking, reservation, and allocation of these numbers, with the primary aim of benefiting wireless service providers by streamlining their inventory management processes.

**1.3 Definitions**

For clarity, here are some key definitions:

- ICCID (Integrated Circuit Card Identifier): A unique identifier associated with SIM cards, vital for their identification and functionality.
- example:-     8991000904443084459U (MMCC-IINN-NNNN-NNNN-NN-C(X) format where,
- MM = Constant (ISO 7812 Major Industry Identifier)
- CC = Country Code
- II = Issuer Identifier
- N{12} = Account ID ("SIM number")
- C = Checksum calculated from the other 19 digits using the Luhn algorithm.
- x = An extra 20th digit is returned by the 'AT!ICCID?' command, but it is not officially part of the ICCID.)
- MSISDN (Mobile Station International Subscriber Directory Number): A mobile phone number within the global cellular network, used to route calls and messages.
- IMEI (International Mobile Equipment Identity): A globally unique identifier assigned to mobile devices, crucial for device identification and tracking.


 ## **2. System Description**

**2.1 System Overview**

The system consists of two primary components: a backend developed using Java Spring Boot and a frontend built with React.js. Together, they provide the capability for users to efficiently reserve and manage wireless numbers.

**2.2 System Architecture**

The system follows a client-server architecture with a RESTful API, facilitating seamless communication between the frontend and backend components

**2.3 Data Flow**

The system's data flow begins with user input through the frontend. This data is then processed in the backend, where it undergoes various operations. Subsequently, the processed results are relayed back to the frontend for display.

**2.4 Technology Stack**

- The system's technology stack comprises the following key components:
- Backend: Java 11, Spring Boot 2.7.13
- Frontend: React.js
- Database: MySQL (MySQLdatabase technology)

## **3. Functional Requirements**

**3.1 Use Cases**

The system encompasses several essential functionalities, including:

- Reservation of Numbers: Users can reserve ICCID, MSISDN, and IMEI numbers, ensuring their availability for future use.
- Allocation to Customers: When numbers are allocated to customers, the system will associate ICCID with the corresponding MSISDN and IMEI.
- Tracking and Availability: The system diligently tracks reservation dates and monitors the availability of numbers, aiding users in making informed decisions regarding number allocation.

## **4. Non-Functional Requirements**

**4.1 Performanc**e

The system's performance expectations include:

Responsiveness: The system should exhibit responsiveness, ensuring that actions such as number reservation are carried out with minimal latency.

**4.2 Security**

Security is of paramount importance in the Wireless Inventory Number Management system. 
It encompasses several measures to safeguard sensitive data and control access to the system effectively.

***Authentication and Authorization***

Authentication and authorization mechanisms will be implemented to ensure that users have controlled access to the system. This is achieved through the use of JSON Web Tokens (JWT), which provide a secure and efficient means of user authentication and authorization.

**Authentication:**

- JWT-Based Authentication: The system will employ JWT-based authentication to verify the identity of users during login. Upon successful authentication, the system will issue a JWT token to the user.
- User Credentials: Users will be required to provide valid credentials, typically a username and password, during login.
- Token Expiry: JWT tokens will have a predefined expiry time, after which they will need to be renewed or reacquired.

**Authorization:**

- Role-Based Access Control (RBAC): The system will adopt Role-Based Access Control, where users are assigned specific roles (e.g., admin, regular user). These roles determine the level of access a user has within the system.
- Authorization Claims: JWT tokens will contain claims or attributes specifying a user's role and permissions. The system will use these claims to enforce access control policies.
- Resource Protection: Authorization rules will be in place to protect sensitive resources, ensuring that users can only access data and perform actions for which they have the appropriate permissions.

**4.3 User Interface**

The user interface design will adhere to the following criteria:

- User-Friendly Design: The user interface will be intuitively designed for ease of use.
- Responsiveness: The interface will be responsive to various screen sizes and devices, ensuring a consistent user experience.

**4.4 Database**

The system's database requirements encompass the following:

Database Technology: The choice of database technology (e.g., MySQL) will be made with data security and efficiency in mind.

**4.5 Compatibility**

The system will ensure compatibility as follows:

Cross-Browser Compatibility: The frontend will be designed to function seamlessly across different web browsers, providing a consistent experience to all users.

## **5. User Interface Design**

- The user interface will prioritize simplicity and user-friendliness. It will include:
- Simple Forms: The UI will incorporate straightforward forms for number reservation.
- Intuitive Layout: The layout will be designed with user intuitiveness in mind, enhancing the tracking of reservations.

## **6. Data Model**

The data model will consist of key entities for ICCID, MSISDN, and IMEI, securely stored within the chosen database system.

Need to add ER image from database

## **7. API Specification**

The system will define RESTful API endpoints with specifications covering:

- Reserving Numbers: API endpoints will be provided for reserving ICCID, MSISDN, and IMEI numbers.
- Association: APIs will enable the association of ICCID with corresponding MSISDN and IMEI.
- Availability Checks: Endpoints for checking the availability of numbers will also be included.

## **8. Security**

Security measures will be implemented, including:

-Authentication: Users will be required to authenticate themselves before accessing the system.

-Authorization: Authorization mechanisms will control user access based on defined roles and permissions.

-Data Encryption: Sensitive data transmission will be encrypted to protect against unauthorized access.

## **9. Testing**

A comprehensive testing strategy will be employed, encompassing the following:

- Unit Testing: Testing of individual components, ensuring their functionality in isolation.
- Integration Testing: Verification of interactions between different system components.
- User Acceptance Testing: Evaluation of the system's compliance with user requirements and expectations.

## **10. Deployment**

Deployment of the system will involve the following:

- Deployment Architecture: The architecture for deploying the backend and frontend in a production environment will be determined.
- Deployment Instructions: Detailed instructions will be provided to guide the deployment process, ensuring successful implementation.

## **Data Encryption**

To ensure the confidentiality of sensitive data transmitted between the frontend and backend components, the system will employ data encryption.

- Secure Communication: All data transferred between the frontend and backend will be transmitted over secure, encrypted connections using HTTPS (SSL/TLS) protocols.
- End-to-End Encryption: Sensitive information, such as user credentials and authentication tokens, will be encrypted before transmission, making it nearly impossible for unauthorized parties to intercept or decipher the data in transit.
- These security measures, including JWT-based authentication and data encryption, will be implemented rigorously to safeguard user data and protect the integrity and security of the Wireless Inventory Number Management system.


## Getting Started

To get started with the Wireless Inventory Number Management system, follow these steps:
1. Clone the repository.
2. Set up the backend according to the provided instructions.
3. Set up the frontend and configure the connection to the backend.
4. Deploy the system as per the deployment instructions.

## Contributing

Contributions to this project are welcome. Please follow the contribution guidelines provided in the repository.


## Q & A :  Essential to ask questions that helps clarify the project requirements

**Project Objective:**

1. What is the main goal of the web project?
2. What problem or need does it address?
3. What are the expected outcomes or deliverables?

**Target Audience:**
Do we have user personas or profiles to consider?

**Functionality and Features:**

1. What specific features and functionality should the website have?
2. Are there any interactive elements(both external & on server) or special requirements?

**Content and Information:**

1. What content and information should be included on the website?
2. Is content provided, or should it be created?

**Design and User Experience:**

1. Are there any design preferences or brand guidelines to follow?
2. How should the user experience be optimized for the target audience?

**Hosting and Domain:**

1. Have hosting and domain considerations been made?
2. Should the website be hosted on a specific platform?

**Security and Privacy:**

1. What security measures need to be in place to protect user data?
2. Are there privacy regulations to comply with?

