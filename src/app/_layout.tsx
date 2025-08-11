import { Tabs } from "expo-router";
import { StatusBar } from "expo-status-bar";
import React from "react";
import Ionicons from '@expo/vector-icons/Ionicons';
import MaterialCommunityIcons from '@expo/vector-icons/MaterialCommunityIcons';
import FontAwesome6 from '@expo/vector-icons/FontAwesome6';
import AntDesign from '@expo/vector-icons/AntDesign';

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
                        tabBarIcon: ({ color, size }) => (
                            <Ionicons name="home-outline" size={size} color={color} />
                        )
                    }}    
                />
                <Tabs.Screen
                    name="food"
                    options={{
                        title: "Food",
                        headerShown: false,
                        tabBarIcon: ({ color, size }) => (
                            <MaterialCommunityIcons name="chef-hat" size={size} color={color} />
                        )
                    }}    
                />
                <Tabs.Screen
                    name="workouts"    
                    options={{
                        title: "Food",
                        headerShown: false,
                        tabBarIcon: ({ color, size }) => (
                            <FontAwesome6 name="dumbbell" size={size} color={color} />
                        )
                    }}
                />
                <Tabs.Screen
                    name="profile"
                    options={{
                        title: "Profile",
                        headerShown: false,
                        tabBarIcon: ({ color, size }) => (
                            <AntDesign name="profile" size={size} color={color} />
                        )
                    }}
                />
            </Tabs>
        </React.Fragment>
    );
}