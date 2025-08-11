import { Stack, usePathname } from "expo-router";

export default function Layout(){
    const pathname = usePathname();

    return(
        <Stack screenOptions={{ animation: "none" }}>
            <Stack.Screen name="index" options={{ title: "Food"}} />
            <Stack.Screen name="addFood" options={{ title: "Add Food"}} />
            <Stack.Screen name="createRecipe" options={{ title: "Create Recipe"}} />
            <Stack.Screen name="barcodeScan" options={{ title: "Scan Barcode"}} />
        </Stack>
    );
}