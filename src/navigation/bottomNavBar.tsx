import React from 'react';
import {View, TouchableOpacity, Text, StyleSheet} from 'react-native';
import { Link } from 'expo-router';
import { colors, commonStyles } from '../styles';
import { Ionicons, MaterialIcons } from '@expo/vector-icons';
import FontAwesome5 from '@expo/vector-icons/FontAwesome5';
import FontAwesome6 from '@expo/vector-icons/FontAwesome6';
import MaterialCommunityIcons from '@expo/vector-icons/MaterialCommunityIcons';

export default function BottomNavBar() {
  return (
    <View style={[commonStyles.navbar, styles.specific]}>
      <Link href="/home" asChild>
      <TouchableOpacity style={styles.navItem}>
        <Ionicons name="home" size={48} color={colors.secondary} style={styles.navbarText} />
      </TouchableOpacity>
      </Link>

      <Link href="/food" asChild>
        <TouchableOpacity style={styles.navItem}>
          <FontAwesome6 name="bowl-food" size={48} color={colors.secondary} style={styles.navbarText} />
        </TouchableOpacity>
      </Link>

      <Link href="/workouts" asChild>
        <TouchableOpacity style={styles.navItem}>
          <FontAwesome5 name="dumbbell" size={48} color={colors.secondary} style={styles.navbarText} />
        </TouchableOpacity>
      </Link>
      
      <Link href="/profile" asChild>
          <TouchableOpacity style={styles.navItem}>
              <MaterialCommunityIcons name="face-man-profile" size={48} color={colors.secondary} />
          </TouchableOpacity>
      </Link>

    </View>
  );
}

const styles = StyleSheet.create({
  specific: {
    borderBottomWidth: 45,
  },
  navbarText: {
    fontSize: 36,
    fontWeight: 'bold',
    color: colors.secondary,
  },
  navItem: {
    flexDirection: 'row',
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});