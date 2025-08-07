import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import { colors, commonStyles, typography } from '../styles';

export default function HomeScreen() {
  return (
    <View style={commonStyles.container}>
      <Text style={commonStyles.title}>Home Screen</Text>
      <StatusBar style="auto" />
    </View>
  );
}
