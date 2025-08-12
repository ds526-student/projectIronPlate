import { Stack, usePathname } from "expo-router";
import { colors } from "../../styles/colors";

export default function Layout(){
    const pathname = usePathname();

    return(
        <Stack screenOptions={{ animation: "none" }}>
            <Stack.Screen 
                name="index" 
                options={{ 
                    title: "Food",
                    headerTintColor: colors.text,
                    headerStyle: {
                        backgroundColor: colors.primary
                    }
                }} 
            />
            <Stack.Screen 
                name="addFood" 
                options={{ 
                    title: "Add Food",
                    headerTintColor: colors.text,
                    headerStyle: {
                        backgroundColor: colors.primary
                    }
                }} 
            />
            <Stack.Screen 
                name="createRecipe" 
                options={{ 
                    title: "Create Recipe",
                    headerTintColor: colors.text,
                    headerStyle: {
                        backgroundColor: colors.primary
                    }
                }} 
            />
            <Stack.Screen 
                name="barcodeScan" 
                options={{ 
                    title: "Scan Barcode",
                    headerTintColor: colors.text,
                    headerStyle: {
                        backgroundColor: colors.primary
                    }
                }} 
            />
            <Stack.Screen 
                name="addFavFood" 
                options={{ 
                    title: "Add Favourite Food",
                    headerTintColor: colors.text,
                    headerStyle: {
                        backgroundColor: colors.primary
                    }
                }} 
            />
        </Stack>
    );
}