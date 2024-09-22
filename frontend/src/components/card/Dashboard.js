import React from "react";
import Card from "./Card";

const Dashboard = () => {
  return (
    <div className="dash-container">
      <Card
        id="doorList"
        imgSrc="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIzMiIgaGVpZ2h0PSIzMiIgdmlld0JveD0iMCAwIDMyIDMyIj4KICA8ZyBmaWxsPSJub25lIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiPgogICAgPHJlY3Qgd2lkdGg9IjMyIiBoZWlnaHQ9IjMyIiBmaWxsPSIjRkZDMTQ4IiByeD0iMyIvPgogICAgPHBhdGggc3Ryb2tlPSIjMDAwIiBzdHJva2UtbGluZWNhcD0ic3F1YXJlIiBzdHJva2Utd2lkdGg9IjIiIGQ9Ik0yMS41IDFMMjEuNSAzMU0xMC41IDFMMTAuNSAzMSIvPgogIDwvZz4KPC9zdmc+Cg=="
        title="Check In/Out"
        bgColor="#f7f8f9"
      />
      <Card
        id="report"
        imgSrc="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIzMiIgaGVpZ2h0PSIzMiIgdmlld0JveD0iMCAwIDMyIDMyIj4KICA8ZyBmaWxsPSJub25lIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiPgogICAgPHJlY3Qgd2lkdGg9IjMyIiBoZWlnaHQ9IjMyIiBmaWxsPSIjMUNEQjlFIiByeD0iMyIvPgogICAgPHJlY3Qgd2lkdGg9IjExIiBoZWlnaHQ9IjExIiB4PSIyMCIgeT0iMjAiIHN0cm9rZT0iIzAwMCIgc3Ryb2tlLXdpZHRoPSIyIi8+CiAgICA8cmVjdCB3aWR0aD0iMTEiIGhlaWdodD0iMTEiIHg9IjEiIHk9IjEiIHN0cm9rZT0iIzAwMCIgc3Ryb2tlLXdpZHRoPSIyIi8+CiAgPC9nPgo8L3N2Zz4K"
        title="Biểu đồ"
        bgColor="#f7f8f9"
      />
      <Card
        id="setting"
        imgSrc="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIzMiIgaGVpZ2h0PSIzMiIgdmlld0JveD0iMCAwIDMyIDMyIj4KICA8ZyBmaWxsPSJub25lIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiPgogICAgPHJlY3Qgd2lkdGg9IjEyIiBoZWlnaHQ9IjE2IiB4PSIyMCIgeT0iMTYiIGZpbGw9IiM0M0E0RkYiIHJ4PSIzIi8+CiAgICA8cmVjdCB3aWR0aD0iMzIiIGhlaWdodD0iMTEiIGZpbGw9IiM0M0E0RkYiIHJ4PSIzIi8+CiAgICA8cmVjdCB3aWR0aD0iMTEiIGhlaWdodD0iMTQiIHg9IjEiIHk9IjE3IiBzdHJva2U9IiMwMDAiIHN0cm9rZS13aWR0aD0iMiIvPgogIDwvZz4KPC9zdmc+Cg=="
        title="Tùy chỉnh"
        bgColor="#f7f8f9"
      />
    </div>
  );
};

export default Dashboard;
