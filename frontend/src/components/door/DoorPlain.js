import React, { useState, useEffect } from 'react';
import './Door.css'; // Import the CSS file



export default function DoorComponent() {

  return (
    <div className="door-container">    
        <div id="door" className="door">
            <div className={`door-front`}>
            <div className="knob-plain"></div>
            </div>
            <div className="door-back"></div>
        </div>
    </div>
  );
};