import React from 'react';

import { Navigate } from 'react-router-dom';

const isAuthenticated = () => {
    return localStorage.getItem('auth_token') !== null;
};
  
const PrivateRoute = ({ element: Element, ...rest }) => (
  isAuthenticated() ? <Element /> : <Navigate to="/" />
);
  
export default PrivateRoute;