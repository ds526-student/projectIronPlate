import React from 'react';
import { View, Text } from 'react-native';
import { commonStyles } from '../../styles/commonStyles';

export default function HomeScreen() {
    return(
        <View style={{flex: 1, justifyContent: 'center', padding: 4}}>
            <Text style={commonStyles.centerText}>Index</Text>
        </View>
    );  
}