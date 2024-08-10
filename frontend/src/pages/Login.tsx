import React from 'react';
import { Link } from 'react-router-dom';
import '../App.css';  // Import the CSS file

const Login: React.FC = () => {
  return (
    <div className="form-container">
      <h2>Welcome to Rafee's Reimbursement Program</h2>
      <h2>Login</h2>
      <form>
        <div className="form-group">
          <label htmlFor="username">Username</label>
          <input type="text" id="username" placeholder="Enter your username" />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input type="password" id="password" placeholder="Enter your password" />
        </div>
        <div className="form-group button">
          <button id="login" type="submit">Login</button>
        </div>
        <div className="form-group link">
          <span>Don't have an account? <Link to="/create-account">Create Account</Link></span>
        </div>
      </form>
    </div>
  );
};

export default Login;