import React, { useState, useEffect } from 'react';
import './Door.css'; // Import the CSS file
import { request, setAuthHeader } from '../../helpers/axios_helper';
import { useNavigate } from 'react-router-dom';



const DoorPlain = ({ onClick, door }) => (   
    <div className="door-plain-container">
        <div class="dummy-door"></div>
        <div className={`door-front-plain`} onClick={onClick}>
            <div className="door-text">{door.lockAlias}</div>
            <div className="knob-plain"></div>
        </div>
    </div>
  );



export default function DoorList() {
    const [items, setItems] = useState([]);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const handleDoorClick = (door) => {
        navigate('/checkin', { state: { door } });
    };

    useEffect(() => {
        request(
            "GET",
            "/locks",
            {}).then(
            (response) => {
                setItems(response.data);
            });
      }, []);

    if (error) return <p>Error: {error}</p>;

    return (
        <div className='door-list'>     
                {items.length === 0 ? (
                    <p>Không tìm thấy khóa nào.</p>
                ) : (
                    items.map(item => <DoorPlain key={item.lockId} door={item} onClick={() => handleDoorClick(item)}/>)
                )}
            
        </div>
    );
};