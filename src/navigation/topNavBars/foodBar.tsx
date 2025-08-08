import React from 'react';
import {View, TouchableOpacity, Text, StyleSheet} from 'react-native';
import { Link } from 'expo-router';
import { colors, commonStyles } from '../../styles';

export default function TopNavBar() {

  return (
    <View style={[commonStyles.navbar, styles.specific]}>
        <Text style={styles.navbarText}>Food</Text>
    </View>
    );
}

    
const styles = StyleSheet.create({
    specific: {
    borderTopWidth: 34,
    },
    navbarText: {
        fontSize: 24,
        fontWeight: 'bold',
        color: colors.text,
        textAlign: 'center',
        flex: 1,
        paddingVertical: 15,
    },
});