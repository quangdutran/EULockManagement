import React from "react";
import ResponsiveAppBar from "../bar/ResponsiveAppBar";
import DoorList from "./DoorList";

export default function DoorListComponent() {
    return (
        <div>
            <ResponsiveAppBar />
            <h2>Danh sách khóa</h2>
            <DoorList />
        </div>
    );
}