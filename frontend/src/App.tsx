import React from 'react';
import logo from './logo.svg';
import './App.css';


import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';

import Navbar from './components/Navbar';
import Login from './pages/Login';
import CreateAccount from './pages/CreateAccount';





const App: React.FC = () => {
  const userType = 'Manager'; // Example: Replace with actual user type from your auth logic

  return (
    <Router>
      <div className="container mx-auto mt-4">
        <Routes>
          {/* Redirect to Login by default */}
          <Route path="/" element={<Navigate to="/login" />} />
          {/* Login Page */}
          <Route path="/login" element={<Login />} />
          {/* Create Account Page */}
          <Route path="/create-account" element={<CreateAccount />} />
          {/* Add other routes as needed */}
        </Routes>
      </div>
    </Router>
  );
};

export default App;
