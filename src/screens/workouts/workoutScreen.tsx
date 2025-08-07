import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import { colors, commonStyles } from '../../styles';

export default function WorkoutScreen() {
  return (
    <View style={[commonStyles.container, styles.screen]}>
      <Text style={commonStyles.title}>Main Workout Screen</Text>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  screen: {
    flex: 1,
  },
});
