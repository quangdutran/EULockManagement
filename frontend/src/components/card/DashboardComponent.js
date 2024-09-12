import React from "react";
import ResponsiveAppBar from "../bar/ResponsiveAppBar";
import Dashboard from "./Dashboard";

export default function DashboardComponent() {
    return (
        <div>
            <ResponsiveAppBar />
            <Dashboard />
        </div>
    );
}