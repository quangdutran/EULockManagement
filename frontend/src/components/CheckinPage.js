import React, { useState, useEffect } from 'react';
import DoorComponent from './door/Door';
import CheckinForm from './form/CheckinForm';
import ResponsiveAppBar from './bar/ResponsiveAppBar';
import { useLocation, useNavigate } from 'react-router-dom';
import swal from 'sweetalert';
import { request } from '../helpers/axios_helper';



export default function CheckinPage() {
    const location = useLocation();
    const navigate = useNavigate();
    const { door } = location.state || {};
    const [stay, setStay] = useState(null);
    const [loading, setLoading] = useState(true);

    if (!door.lockId) {
        swal("Failed", "Không nhận được thông tin khóa", "error").then(navigate('/dashboard'));
    }

    useEffect(() => {
        request(
            "GET",
            "/stay/" + door.lockId,
            {
                
            }).then(
            (response) => {
                setStay(response.data);
            }).finally(setLoading(false));
      }, []);

    return (
        <div>
            <ResponsiveAppBar />
            <h3 className="aligned-center">{door.lockAlias}</h3>
            {loading ? (
                    <p>Đang lấy thông tin lưu trú.</p>
                ) : (
                    <div className='container-center top3rem'>
                        <DoorComponent lockId={door.lockId} />
                        <CheckinForm stay={stay} lockId={door.lockId} />
                    </div>
                )}
           
        </div>
    );
}