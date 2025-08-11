import { Tabs } from "expo-router";
import { StatusBar } from "expo-status-bar";
import React from "react";

export default function RootLayout() {
    return(
        <React.Fragment>
            <StatusBar />
            <Tabs>
                <Tabs.Screen
                    name="(home)"
                    options={{
                        title: "Home",
                        headerShown: false,
                    }}    
                />
                <Tabs.Screen
                    name="food"
                    options={{
                        title: "Food",
                        headerShown: false,
                    }}    
                />
                <Tabs.Screen
                    name="workouts"    
                    options={{
                        title: "Food",
                        headerShown: false,
                    }}
                />
                <Tabs.Screen
                    name="profile"
                    options={{
                        title: "Profile",
                        headerShown: false,
                    }}
                />
            </Tabs>
        </React.Fragment>
    );
}