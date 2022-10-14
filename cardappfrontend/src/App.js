import logo from './logo.svg';
import './App.css';
import React, { Component } from 'react';
import CardList from './components/CardList';
 
function App() {
    return (
        <div className="container-fluid">
          <nav>
            <div className='nav-wrapper center-allign'>
                <p className='brand-logo'>Credit Card System</p>
            </div>
          </nav>
          <div className='row'>
            <CardList/>
          </div>
        </div>
    );
}
export default App;
