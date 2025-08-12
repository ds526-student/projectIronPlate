import { Stack } from "expo-router";
import { colors } from "../../styles/colors";

export default function Layout(){
    return(
        <Stack>
            <Stack.Screen 
                name="index" 
                options={{ 
                    title: "Profile",
                    headerTintColor: colors.text,
                    headerStyle: {
                        backgroundColor: colors.primary
                    }
                }} 
            />
        </Stack>
    );
}