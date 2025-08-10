import { Tabs } from "expo-router";
import { StatusBar } from "expo-status-bar";
import React from "react";

export default function RootLayout() {
    return(
        <React.Fragment>
            <StatusBar />
            <Tabs>
                <Tabs.Screen
                    name="index"    
                />
                <Tabs.Screen
                    name="food"    
                />
                <Tabs.Screen
                    name="workouts"    
                />
                <Tabs.Screen
                    name="profile"    
                />
            </Tabs>
        </React.Fragment>
    );
}