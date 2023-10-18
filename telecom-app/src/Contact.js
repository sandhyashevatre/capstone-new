import { Component } from "react";
import './Contact.css';

class Contact extends Component{
    render(){
        return(
            <>
             {/* Footer */}
            <div className="social">
                <div className="social-links">
                    <a href="https://in.linkedin.com/in/sandhya-shevatre-290397207" target="_blank" rel="noopener noreferrer">LinkedIn</a>
                    <a href="https://github.com/sandhyashevatre" target="_blank" rel="noopener noreferrer">GitHub</a>
                    <a href="https://www.instagram.com/swarajika_143/" target="_blank" rel="noopener noreferrer">Instagram</a>
                    {/* Add links to other social profiles as needed */}
                </div>
            </div>
            </>
        );
    }
}
export default Contact;