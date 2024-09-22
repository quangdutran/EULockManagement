import React, { useState } from "react";
import { TextField, Button, Stack } from "@mui/material";
import swal from 'sweetalert';
import { Loader } from 'react-overlay-loader';
import { useNavigate } from 'react-router-dom';
import { request } from '../../helpers/axios_helper';



function addDays(date, days) {
  const newDate = new Date(date);
  newDate.setDate(newDate.getDate() + days);
  return newDate;
}

const CheckinForm = ({stay, lockId}) => {
  const hasStay = stay !== null && stay !== undefined && stay !== ""
  console.log(stay)
  const navigate = useNavigate();

  const [customerName, setCustomerName] = useState("");
  const [customerEmail, setCustomerEmail] = useState("");
  const [customerPhone, setCustomerPhone] = useState("");
  const [days, setDays] = useState("");
  const [loading, setLoading] = useState(false);

  function handleSubmit(event) {
    event.preventDefault();
    setLoading(true)
    const now = new Date();
    const checkoutDate = addDays(now, days);
    request(
        "POST",
        "/stay/checkin",
        {
          "lockId": lockId,
          "checkInTime": now.toISOString(),
          "checkoutTime": checkoutDate.toISOString(),
          "customerName": customerName,
          "customerPhone": customerPhone,
          "customerEmail": customerEmail
      }).then(
        (response) => {
          swal("Success", "Checkin xong, nhớ lấy mã cho khách nhé", "success")
          .then(window.location.reload());
        }).finally(() => setLoading(false));
  }

  function handleCheckout(event) {
    event.preventDefault();
    setLoading(true)
    request(
        "POST",
        "/stay/"+ stay.id +"/checkout",
        {}).then(
        (response) => {
          swal("Success", "Checkout xong, tổng tiền là" + response.data.price, "success")
            .then(navigate('/dashboard'));
        }).finally(() => setLoading(false));
  }

  return (
    <React.Fragment>
      <Loader fullPage  loading={loading}/>
      {hasStay ? (
        <form onSubmit={handleCheckout}>
          <Stack spacing={2} direction="row" sx={{ marginBottom: 4 }}>
            <TextField
              type="text"
              variant="outlined"
              color="secondary"
              value={stay.customerName}
              fullWidth
              contentEditable={false}
            />
          </Stack>
          <TextField
            type="email"
            variant="outlined"
            color="secondary"
            value={stay.customerEmail}
            contentEditable={false}
            fullWidth
            sx={{ mb: 4 }}
          />
          <TextField
            type="tel"
            variant="outlined"
            color="secondary"
            contentEditable={false}
            value={stay.customerPhone}
            required
            fullWidth
            sx={{ mb: 4 }}
          />
          <Button variant="outlined" color="secondary" type="submit">
            Check out
          </Button>
      </form>
      ) : (
        <form onSubmit={handleSubmit}>
        <Stack spacing={2} direction="row" sx={{ marginBottom: 4 }}>
          <TextField
            type="text"
            variant="outlined"
            color="secondary"
            label="Tên"
            onChange={(e) => setCustomerName(e.target.value)}
            value={customerName}
            fullWidth
            required
          />
        </Stack>
        <TextField
          type="email"
          variant="outlined"
          color="secondary"
          label="Email"
          onChange={(e) => setCustomerEmail(e.target.value)}
          value={customerEmail}
          fullWidth
          sx={{ mb: 4 }}
        />
        <TextField
          type="tel"
          variant="outlined"
          color="secondary"
          label="Số điện thoại"
          onChange={(e) => setCustomerPhone(e.target.value)}
          value={customerPhone}
          required
          fullWidth
          sx={{ mb: 4 }}
        />
        <TextField
          type="number"
          variant="outlined"
          color="secondary"
          label="Số ngày ở dự kiến"
          value={days}
          onChange={(e) => setDays(e.target.value)}
          required
          fullWidth
          sx={{ mb: 4 }}
        />
        <Button variant="outlined" color="secondary" type="submit">
          Check in
        </Button>
      </form>
      )} 
    </React.Fragment>
  );
};

export default CheckinForm;
