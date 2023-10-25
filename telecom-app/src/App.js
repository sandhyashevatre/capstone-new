import { BrowserRouter, Link, Route, Routes } from "react-router-dom";
import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHouse } from "@fortawesome/free-solid-svg-icons";
import { faUser } from "@fortawesome/free-solid-svg-icons";

import "./App.css";
import Data from "./Data";
import Home from "./Home";
import About from "./About";
import Account from "./Account";
import Prepaid from "./Prepaid";
import Postpaid from "./Postpaid";
import ImeiManager from "./ImeiManager";
import ReplaceSim from "./ReplaceSim";
import PortNetwork from "./PortNetwork";
import Reservation from "./Reservation";
import InactiveRecord from "./InactiveRecord";
import ReservRecord from "./ReservRecord";

export default function App() {
  const [userState, setUserState] = useState("LOGIN");

  const toggleUserState = () => {
    setUserState(userState === "LOGIN" ? "LOGOUT" : "LOGIN");
  };

  useEffect(() => {
    if (userState === "LOGOUT") {
      localStorage.removeItem("token");
    }
  }, [userState]);
  return (
    <>
      <BrowserRouter>
        <div>
          <nav className="navbar">
            <ul className="navbar">
              <FontAwesomeIcon icon={faHouse} size="lg" />
              <Link to="/home" className="navbar-brand">
                Home
              </Link>
              <li className="nav-item dropdown">
                <span className="nav-link">
                  Prepaid
                  <div className="dropdown">
                    <ul className="dropdown-menu">
                      <li>
                        <Link to="/prepaid" className="dropdown-item">
                          New SIM registration
                        </Link>
                      </li>
                      <li>
                        <Link
                          to="/portNetwork-prepaid"
                          className="dropdown-item"
                        >
                          Port SIM card
                        </Link>
                      </li>
                    </ul>
                  </div>
                </span>
              </li>
              <li className="nav-item dropdown">
                <span className="nav-link">
                  Postpaid
                  <div className="dropdown">
                    <ul className="dropdown-menu">
                      <li>
                        <Link to="/postpaid" className="dropdown-item">
                          New SIM registration
                        </Link>
                      </li>
                      <li>
                        <Link
                          to="/portNetwork-postpaid"
                          className="dropdown-item"
                        >
                          Port SIM card
                        </Link>
                      </li>
                    </ul>
                  </div>
                </span>
              </li>
              <li className="nav-item dropdown">
                <span className="nav-link">
                  Reservation
                  <div className="dropdown">
                    <ul className="dropdown-menu">
                      <li>
                        <Link
                          to="/prepaid-reservation"
                          className="dropdown-item"
                        >
                          Reserve prepaid{" "}
                        </Link>
                      </li>
                      <li>
                        <Link
                          to="/postpaid-reservation"
                          className="dropdown-item"
                        >
                          Reserve postpaid{" "}
                        </Link>
                      </li>
                    </ul>
                  </div>
                </span>
              </li>
              <li className="nav-item dropdown">
                <span className="nav-link">
                  Records
                  <div className="dropdown">
                    <ul className="dropdown-menu">
                      <li>
                        <Link to="/prepaid-data" className="dropdown-item">
                          Prepaid
                        </Link>
                      </li>
                      <li>
                        <Link to="/postpaid-data" className="dropdown-item">
                          Postpaid
                        </Link>
                      </li>
                      <hr></hr>
                      <li>
                        <Link to="/insert-sim" className="dropdown-item">
                          Reserve IMEI for a number
                        </Link>
                      </li>
                    </ul>
                  </div>
                </span>
              </li>
              <li className="nav-item">
                <Link to="/ReservRecord" className="nav-link">
                  Reserved-SIM
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/InactiveRecord" className="nav-link">
                Inactive-SIM
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/about" className="nav-link">
                  About
                </Link>
              </li>
            </ul>
            <div className="nav-item">
              <Link to=""></Link>
            </div>
            <div className="nav-login">
              <Link
                to="/account"
                onClick={toggleUserState}
                className="nav-link"
              >
                {userState}
                <FontAwesomeIcon icon={faUser} size="xl" />
              </Link>
            </div>
          </nav>
          <hr />
          <Routes>
            <Route path="/home" element={<Home />} />
            <Route path="/about" element={<About />} />
            <Route path="/prepaid" element={<Prepaid />} />
            <Route path="/postpaid" element={<Postpaid />} />
            <Route path="/account" element={<Account />} />
            <Route path="insert-sim" element={<ImeiManager />} />
            <Route path="/ReservRecord" element={<ReservRecord />} />
            <Route path="/InactiveRecord" element={<InactiveRecord />} />
            <Route
              path="/prepaid-reservation"
              element={<Reservation connection={{ type: "prepaid" }} />}
            />
            <Route
              path="/postpaid-reservation"
              element={<Reservation connection={{ type: "postpaid" }} />}
            />
            <Route
              path="portNetwork-prepaid"
              element={<PortNetwork connection={{ type: "prepaid" }} />}
            />
            <Route
              path="portNetwork-postpaid"
              element={<PortNetwork connection={{ type: "postpaid" }} />}
            />
            <Route
              path="prepaid-data"
              element={<Data connection={{ type: "prepaid" }} />}
            />
            <Route
              path="postpaid-data"
              element={<Data connection={{ type: "postpaid" }} />}
            />
            <Route
              path="replace-sim-prepaid"
              element={<ReplaceSim connection={{ type: "prepaid" }} />}
            />
            <Route
              path="replace-sim-postpaid"
              element={<ReplaceSim connection={{ type: "postpaid" }} />}
            />
          </Routes>
        </div>
      </BrowserRouter>

      <div className="footer">
        <div className="social-links">
          <a
            href="https://in.linkedin.com/in/sandhya-shevatre-290397207"
            target="_blank"
            rel="noopener noreferrer"
          >
            LinkedIn
          </a>
          <a
            href="https://github.com/sandhyashevatre"
            target="_blank"
            rel="noopener noreferrer"
          >
            GitHub
          </a>
          <a
            href="https://www.instagram.com/swarajika_143/"
            target="_blank"
            rel="noopener noreferrer"
          >
            Instagram
          </a>
        </div>
      </div>
    </>
  );
}
