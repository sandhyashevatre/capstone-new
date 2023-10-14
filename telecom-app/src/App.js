import { Component } from "react";
import { BrowserRouter, Link, Route, Routes } from "react-router-dom";
import Home from "./Home";
import Contact from "./Contact";
import Account from "./Account";
import Prepaid from "./Prepaid";
import Postpaid from "./Postpaid";
import Reservations from "./Reservations";

class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <div>
          <h2>Wireless Inventory Management </h2>

          <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <ul className="navbar-nav mr-auto">
              <li>
                <Link to="/home" className="nav-link">
                  Home
                </Link>
              </li>
              <li>
                <Link to="/contact" className="nav-link">
                  Contact
                </Link>
              </li>
              <li>
                <Link to="/about" className="nav-link">
                  Account
                </Link>
              </li>
              <li className="nav-item dropdown">
              <a className="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Postpiad
              </a>
                <ul className="dropdown-menu">
                <li><a className="dropdown-item" href="#">Action</a></li>
                <li><a className="dropdown-item" href="#">Another action</a></li>
                  <li></li>
                </ul>
              </li>
              <li>
                <Link to="/postpaid" className="nav-link">
                  Postpaid
                </Link>
              </li>
              <li>
                <Link to="/reservation" className="nav-link">
                  Postpaid
                </Link>
              </li>
            </ul>
          </nav>
          <hr />

          <Routes>
            <Route exact path="/home" Component={Home} />

            <Route path="contact" Component={Contact} />

            <Route path="about" Component={Account} />

            <Route exact path="/prepaid" Component={Prepaid} />

            <Route path="/postpaid" Component={Postpaid} />

            <Route path="reservations" Component={Reservations} />
          </Routes>
        </div>
      </BrowserRouter>
    );
  }
}

// const imgId = {

//   link : "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/MS_Dhoni_2016.jpg/330px-MS_Dhoni_2016.jpg"

// }

export default App;
