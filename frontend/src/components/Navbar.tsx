import React from 'react';
import { Link } from 'react-router-dom';

interface NavbarProps {
  userType: string; // Define the type of user
}

const Navbar: React.FC<NavbarProps> = ({ userType }) => {
    return (
      <nav className="navbar">
        <div className="navbar-container">
          <Link to="/" className="navbar-brand">
            Employee Reimbursement
          </Link>
          <div className="navbar-links">
            <Link to="/">Home</Link>
            {userType === 'Manager' && <Link to="/users">Users</Link>}
            <Link to="/reimbursements">Reimbursements</Link>
          </div>
        </div>
      </nav>
    );
};


export default Navbar;
