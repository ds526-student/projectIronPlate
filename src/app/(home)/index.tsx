import React from 'react';
import { View, Text } from 'react-native';
import { commonStyles } from '../../styles/commonStyles';
import { colors } from '../../styles/colors';

export default function HomeScreen() {
    return(
        <View style={[commonStyles.container, {flex: 1, justifyContent: 'center'}]}>
            <View style={{flex: 1}}>
                <Text 
                    style={commonStyles.centerText}
                >
                    Hello Name! {'\n'} Let's have a great day today!
                </Text>
            </View>
            <View style={{flex: 1}}>
                <Text 
                    style={commonStyles.centerText}
                >
                    Bodyweight
                </Text>
            </View>
            <View style={{flex: 1}}>
                <Text 
                    style={commonStyles.centerText}
                >
                    Steps
                </Text>
            </View>
            <View style={{flex: 1}}>
                <Text 
                    style={commonStyles.centerText}
                >
                    Water
                </Text>
            </View>
            <View style={{flex: 1}}>
                <Text 
                    style={commonStyles.centerText}
                >
                    Sleep
                </Text>
            </View>
        </View>
    );  
}