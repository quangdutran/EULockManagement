import * as React from "react";
import ResponsiveAppBar from "../bar/ResponsiveAppBar";
import IncomeChart from "./IncomeChart";

export default function IncomeChartComponent() {
    return (
        <div>
            <ResponsiveAppBar />
            <IncomeChart />
        </div>
    );
}