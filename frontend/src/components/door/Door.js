import React, { useState, useEffect } from 'react';
import './Door.css'; // Import the CSS file
import KeyboardAltIcon from "@mui/icons-material/KeyboardAlt";
import { SvgIcon } from "@mui/material";
import { request, setAuthHeader } from '../../helpers/axios_helper';
import swal from 'sweetalert';
import Tooltip from '@mui/material/Tooltip';
import { Loader } from 'react-overlay-loader';



export default function DoorComponent({lockId}) {
  const [isOpen, setIsOpen] = useState(false);
  const [id, setId] = useState(lockId);
  const [doorLoading, setDoorLoading] = useState(false);


  const toggleDoor = () => {
    if (!isOpen) {
        setDoorLoading(true)
        request(
            "POST",
            "/unlock/" + id,
            {}).then(
            (response) => {
                console.log(response)
            }).catch(
            (error) => {
                console.log(error)
                if (error.response.status === 401) {
                    setAuthHeader(null);
                    window.location.reload();
                } else {
                    swal("Failed", error.response.data.message, "error");
                }
            }
        ).finally(
            () => {
                setDoorLoading(false);
                setIsOpen(!isOpen);
            }
        );
    } else {
        setIsOpen(!isOpen);
    }
  };

  const genCode = () => {
    setDoorLoading(true)
    request(
        "GET",
        "/code/" + id,
        {}).then(
        (response) => {
            swal("Success", response.data + "", "Open code");
        }).catch((error) => {
            console.log(error)
            if (error.response.status === 401) {
                setAuthHeader(null);
            } else {
                swal("Failed", error.message, "error");
            }
        }
    ).finally(setDoorLoading(false));
  };

//   useEffect(() => {
//     request(
//         "GET",
//         "/lock",
//         {}).then(
//         (response) => {
//             console.log(response.data)
//             setAlias(response.data.lockAlias);
//             setId(response.data.lockId);
//         }).catch(
//         (error) => {
//             console.log(error)
//             if (error.response.status === 401) {
//                 setAuthHeader(null);
//             } else {
//                 setError({data: error.response.code})
//             }
//         }
//     );
// }, [])

  return (
    <div className="door-container">     
        <Loader fullPage  loading={doorLoading}/>
        <Tooltip title="Nhấn để mở cửa">
        <div id="door" className="door" onClick={toggleDoor}>
            <div className={`door-front ${isOpen ? "open" : ""}`}>
            <div className="knob"></div>
            </div>
            <div className="door-back"></div>
        </div>
        </Tooltip> 
        <div id="pad" className="pad-container" onClick={genCode}>
            <Tooltip title="Nhấn để tạo mã mở cửa">
                <SvgIcon className="svg_icons" >
                <KeyboardAltIcon />
                </SvgIcon>
            </Tooltip>
        </div>
    </div>
  );
};