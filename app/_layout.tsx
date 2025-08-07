import { Stack } from 'expo-router';
import { View, StyleSheet } from 'react-native';
import BottomNavBar from '../src/navigation/bottomNavBar';
import TopNavBar from '../src/navigation/topNavBar';

export default function RootLayout() {
  return (
    <View style={styles.container}>
      <TopNavBar />
      <Stack screenOptions={{ headerShown: false }} />
      <BottomNavBar />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
});