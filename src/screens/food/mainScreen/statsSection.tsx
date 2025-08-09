import { StyleSheet, Text, View } from 'react-native';
import { colors, commonStyles } from '../../../styles';

export default function StatsSection() {
    return (
        <View style={[commonStyles.container]}>
            <Text style={commonStyles.title}>Stats Section</Text>
        </View>
    );
}