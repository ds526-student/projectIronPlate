import { Tabs, usePathname } from 'expo-router';
import { View, StyleSheet } from 'react-native';
import BottomNavBar from '../src/navigation/bottomNavBar';
import HomeBar from '../src/navigation/topNavBars/homeBar';
import FoodBar from '../src/navigation/topNavBars/foodBar';
import WorkoutsBar from '../src/navigation/topNavBars/workoutsBar';
import ProfileBar from '../src/navigation/topNavBars/profileBar';

export default function RootLayout() {
  const pathname = usePathname();

  // Function to determine which top nav bar to render
  const renderTopNavBar = () => {
    switch (pathname) {
      case '/home':
        return <HomeBar />;
      case '/food':
        return <FoodBar />;
      case '/workouts':
        return <WorkoutsBar />;
      case '/profile':
        return <ProfileBar />;
      default:
        return <HomeBar />; // Default fallback
    }
  };

  return (
    <View style={styles.container}>
      {renderTopNavBar()}
      <Tabs screenOptions={{ headerShown: false, tabBarStyle: { display: 'none' } }} />
      <BottomNavBar />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
});