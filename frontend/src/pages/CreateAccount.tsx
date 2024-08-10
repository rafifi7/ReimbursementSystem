import React from 'react';
import { Link } from 'react-router-dom';
import '../App.css';  // Import the CSS file

const CreateAccount: React.FC = () => {
  return (
    <div className="form-container">
      <h2>Welcome to Rafee's Reimbursement Program</h2>
      <h2>Create Account</h2>
      <form>
        <div className="form-group">
          <label htmlFor="username">Username</label>
          <input type="username" id="username" placeholder="Enter your username" />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input type="password" id="password" placeholder="Enter your password" />
        </div>
        <div className="form-group">
          <label htmlFor="confirm-password">Confirm Password</label>
          <input type="password" id="confirm-password" placeholder="Confirm your password" />
        </div>
        <div className="form-group">
            <label htmlFor="first-name">First Name</label>
            <input type="text" id="first-name" placeholder="Enter your first name" />
        </div>
        <div className="form-group">
            <label htmlFor="last-name">Last Name</label>
            <input type="text" id="last-name" placeholder="Enter your last name" />
        </div>
        <div className="form-group">
            <label htmlFor="role">Role</label>
            <input type="text" id="role" placeholder="Enter your role" />
        </div>

        <div className="form-group">
          <button id="createAccount" type="submit">Create Account</button>
        </div>
        <div className="form-group link">
          <span>Already have an account? <Link to="/login">Login</Link></span>
        </div>
      </form>
    </div>
  );
};

export default CreateAccount;
