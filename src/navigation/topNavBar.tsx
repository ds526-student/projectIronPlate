import React from 'react';
import {View, TouchableOpacity, Text, StyleSheet} from 'react-native';
import { Link } from 'expo-router';
import { colors, commonStyles } from '../styles';
import MaterialCommunityIcons from '@expo/vector-icons/MaterialCommunityIcons';
import MaterialIcons from '@expo/vector-icons/MaterialIcons';

export default function TopNavBar() {

  return (
    <View style={[commonStyles.navbar, styles.specific]}>
        <Link href="/profile" asChild>
            <TouchableOpacity style={styles.profileItem}>
                <MaterialCommunityIcons name="face-man-profile" size={48} color={colors.secondary} />
                <Text style={styles.navbarText}>Name Last-Name</Text>
            </TouchableOpacity>
        </Link>

        <Link href="/settings" asChild>
            <TouchableOpacity style={styles.settingsItem}>
                <MaterialIcons name="settings" size={48} color={colors.secondary} />
            </TouchableOpacity>
        </Link>
    </View>
    );
}

    
const styles = StyleSheet.create({
    specific: {
    borderTopWidth: 34,
    },
    navbarText: {
        fontSize: 15,
        fontWeight: 'bold',
        color: colors.secondary,
    },
    profileItem: {
        flexDirection: 'row',
        flex: 1,
        justifyContent: 'flex-start',
        alignItems: 'center',
        paddingHorizontal: 20,
    },
    settingsItem: {
        flexDirection: 'row',
        flex: 1,
        justifyContent: 'flex-end',
        alignItems: 'center',
        paddingHorizontal: 20,
    },
});