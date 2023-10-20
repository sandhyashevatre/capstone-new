import { Component } from "react";
import { BrowserRouter, Link, Route, Routes } from "react-router-dom";
import Home from "./Home";
import About from "./About";
import Account from "./Account";
import Prepaid from "./Prepaid";
import Postpaid from "./Postpaid";
import Data from "./Data";
import ImeiManager from "./ImeiManager";
import ReplaceSim from "./ReplaceSim";
import './App.css';
import PortNetwork from "./PortNetwork";


class App extends Component {
  render() {
    return (
      <>
      <BrowserRouter>
        <div>
          <nav className="navbar">
            <Link to="/home" className="navbar-brand">
              Home
            </Link>
            <ul className="navbar">
              <li className="nav-item">
                <Link to="/about" className="nav-link">
                  About
                </Link>
              </li>
              <li className="nav-item dropdown">
                <span className="nav-link">
                  Prepaid
                  <div className="dropdown">
                    <ul className="dropdown-menu">
                      <li><Link to="/prepaid" className="dropdown-item">New SIM registration</Link></li>
                      <li><Link to="/portNetwork-prepaid" className="dropdown-item">Port SIM card</Link></li>
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
                      <li><Link to="/portNetwork-postpaid" className="dropdown-item">Port SIM card</Link></li>
                    </ul>
                  </div>
                </span>
              </li>
              <li className="nav-item dropdown">
                <span className="nav-link">
                  Records
                  <div className="dropdown">
                    <ul className="dropdown-menu">
                      <li><Link to="/prepaid-data" className="dropdown-item">Prepaid</Link></li>
                      <li><Link to="/postpaid-data" className="dropdown-item">Postpaid</Link></li>
                    </ul>
                  </div>
                </span>
              </li>
            </ul>
            <div className="nav-item">
              <Link to="/account" className="nav-link">
                Login
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
            <Route path="insert-sim" element={<ImeiManager />}/>
            <Route path="portNetwork-prepaid" element={<PortNetwork connection={{type : 'prepaid'}}/>} />
            <Route path="portNetwork-postpaid" element={<PortNetwork connection={{type : 'postpaid'}}/>} />
            <Route path="prepaid-data" element={<Data connection={{type : 'prepaid'}}/>} />
            <Route path="postpaid-data" element={<Data connection={{type : 'postpaid'}}/>} /> 
            <Route path="replace-sim-prepaid" element={<ReplaceSim connection={{type : 'prepaid'}}/>} />      
            <Route path="replace-sim-postpaid" element={<ReplaceSim connection={{type : 'postpaid'}}/>} />
          </Routes>
        </div>
      </BrowserRouter>
      <div>
          <h1>Contact Us</h1>
          <div className="social-links">
            <a href="https://in.linkedin.com/in/sandhya-shevatre-290397207" target="_blank" rel="noopener noreferrer">LinkedIn</a>
            <a href="https://github.com/sandhyashevatre" target="_blank" rel="noopener noreferrer">GitHub</a>
            <a href="https://www.instagram.com/swarajika_143/" target="_blank" rel="noopener noreferrer">Instagram</a>
          </div>         
        </div>
      </>
    );
  }
}
export default App;
