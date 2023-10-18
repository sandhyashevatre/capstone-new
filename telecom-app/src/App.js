import { Component } from "react";
import { BrowserRouter, Link, Route, Routes } from "react-router-dom";
import Home from "./Home";
import Contact from "./Contact";
import Account from "./Account";
import Prepaid from "./Prepaid";
import Postpaid from "./Postpaid";
import Explore from "./Explore";
import './App.css';


class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <div>
          <nav className="navbar">
            <Link to="/home" className="navbar-brand">
              Home
            </Link>
            <ul className="navbar">
              <li className="nav-item">
                <Link to="/contact" className="nav-link">
                  Contact
                </Link>
              </li>
              <li className="nav-item dropdown">
                <span className="nav-link">
                  Prepaid
                  <div className="dropdown">
                    <ul className="dropdown-menu">
                      <li><Link to="/prepaid" className="dropdown-item">New SIM registration</Link></li>
                      <li><a className="dropdown-item" href="#">Post SIM card</a></li>
                    </ul>
                  </div>
                </span>
              </li>
              <li className="nav-item dropdown">
                <span className="nav-link">
                  Postpaid
                  <div className="dropdown">
                    <ul className="dropdown-menu">
                      <li><Link to="/postpaid" className="dropdown-item">New SIM registration</Link></li>
                      <li><a className="dropdown-item" href="#">Post SIM card</a></li>
                    </ul>
                  </div>
                </span>
              </li>
              <li className="nav-item">
                <Link to="/Explore" className="nav-link">
                  Explore
                </Link>
              </li>
            </ul>
            <div className="nav-item">
              <Link to="/account" className="nav-link">
                Account
              </Link>
            </div>
          </nav>
          <hr />
          <Routes>
            <Route path="/home" element={<Home />} />
            <Route path="/contact" element={<Contact />} />
            <Route path="/prepaid" element={<Prepaid />} />
            <Route path="/postpaid" element={<Postpaid />} />
            <Route path="/explore" element={<Explore />} />
            <Route path="/account" element={<Account />} />
          </Routes>
        </div>
      </BrowserRouter>
    );
  }
}
export default App;
