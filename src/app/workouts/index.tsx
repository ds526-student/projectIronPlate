import React from 'react';
import { View, Text } from 'react-native';
import { commonStyles } from '../../styles/commonStyles';

export default function WorkoutsScreen() {
    return (
        <View style={[commonStyles.container, {flex: 1, justifyContent: 'center'}]}>
            <Text 
                style={commonStyles.centerText}
            >
                Workouts
            </Text>
        </View>
    );  
}