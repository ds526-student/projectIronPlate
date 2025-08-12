import React from 'react';
import { View, Text } from 'react-native';
import { commonStyles } from '../../styles/commonStyles';
import { colors } from '../../styles/colors';

export default function HomeScreen() {
    return(
        <View style={[commonStyles.container, {flex: 1, justifyContent: 'center'}]}>
            <Text 
                style={commonStyles.centerText}
            >
                Home
            </Text>
        </View>
    );  
}