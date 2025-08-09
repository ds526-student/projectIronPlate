import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import { colors, commonStyles } from '../../styles';

import StatsSection from './mainScreen/statsSection';
import FunctionalSection from './mainScreen/functionalSection';
import DietSection from './mainScreen/dietSection';

export default function FoodScreen() {
    return (
        <View style={{ flex: 1 }}>
        <View style={{flex: 3}}>
        <StatsSection />
        </View>
        <View style={{flex: 4}}>
        <FunctionalSection />
        </View>
        <View style={{flex: 6}}>
        <DietSection />
        </View>
        </View>
    );
}

const styles = StyleSheet.create({
});
