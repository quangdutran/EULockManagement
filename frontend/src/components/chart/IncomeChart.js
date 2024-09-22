import { BarChart } from "@mui/x-charts/BarChart";
import React, { useState, useEffect } from 'react';
import { request } from '../../helpers/axios_helper';
import { Loader } from 'react-overlay-loader';
import { LineChart } from '@mui/x-charts/LineChart';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';


export default function IncomeChart() {
  const [loading, setLoading] = useState(true);
  const [stays, setStays] = useState([]);
  const [profit, setProfit] = useState([])
  const [indices, setIndices] = useState([])

  const now = new Date();

  const handleChange = (event) => {
    getStays(event.target.value);
  };

  const getStays = (year) => {
    setLoading(true)
    request(
        "GET",
        "/stays/" + year,
        {}).then(
        (response) => {
          setStays(response.data.map(item => item.count));
          console.log(stays)
          setProfit(response.data.map(item => item.totalPrice/1000000));
          setIndices(response.data.map(item => "T" + item.month));
        }).finally(() => setLoading(false));
  }

  useEffect(() => {
    getStays(now.getFullYear());
  }, [])


  if (loading) return (<Loader fullPage loading={true} />)

  return (
    <div>
      <div>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">Năm</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          label="Năm"
          onChange={handleChange}
        >
          <MenuItem value={2023}>2023</MenuItem>
          <MenuItem value={2024}>2024</MenuItem>
        </Select>
      </FormControl>
        <h4>Doanh thu</h4>
        <BarChart
          series={[{ data: profit, type: 'bar', label: 'triệu VNĐ' }]}
          xAxis={[{ scaleType: 'band', data: indices }]}
          height={250}
          margin={{ top: 10, bottom: 30, left: 40, right: 10 }}
        />
        <h4>Lượng khách</h4>
        <LineChart
          xAxis={[{ data: [1, 2] }]}
          series={[
            {
              data: [1,4],
            },
          ]}
          width={500}
          height={300}
        />
      </div>
    </div>
  );
}
