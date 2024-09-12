import axios from 'axios';
import swal from 'sweetalert';


export const getAuthToken = () => {
    return window.localStorage.getItem('auth_token');
};

export const setAuthHeader = (token) => {
    if (token !== null) {
      window.localStorage.setItem("auth_token", token);
    } else {
      window.localStorage.removeItem("auth_token");
    }
};

const isProduction = process.env.REACT_APP_ENV === 'production';

if (isProduction) {
  axios.defaults.baseURL = '/api';
} else {
  axios.defaults.baseURL = 'http://localhost:8080/api';
}

axios.defaults.headers.post['Content-Type'] = 'application/json';


axios.interceptors.response.use(
    response => response,
    error => {
        if (error.response && error.response.status === 401) {
            setAuthHeader(null);
        } else {
            swal("Failed", error.message, "error");
        }
        return Promise.reject(error);
    }
);


export const request = (method, url, data) => {

    let headers = {};
    if (getAuthToken() !== null && getAuthToken() !== "null") {
        headers = {'Authorization': `Bearer ${getAuthToken()}`};
    }

    return axios({
        method: method,
        url: url,
        headers: headers,
        data: data});
};