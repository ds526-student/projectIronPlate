import React from 'react';
import { View, Text, Button, StyleSheet, TouchableOpacity } from 'react-native';
import { commonStyles } from '../../styles/commonStyles';
import { colors } from '../../styles/colors';
import { Link } from 'expo-router';

// icons
import Ionicons from '@expo/vector-icons/Ionicons';

export default function FoodScreen() {
    return(
        <View style={{flex: 1, justifyContent: 'center'}}>
            <View style={[commonStyles.container, {flex: 1, backgroundColor: colors.primary}]}>
                <Text style={[commonStyles.title, commonStyles.centerText]}>Stats Section</Text>
            </View>
            <View style={[commonStyles.container, {flex: 1, backgroundColor: colors.primary}]}>
                <Text style={commonStyles.title}>Skinny Bitch go Eat Something</Text>
            </View>
            <View>
                <TouchableOpacity
                    style={styles.floatingButton}
                >
                <Ionicons name="add-circle" size={65} color={colors.secondary} />
                </TouchableOpacity>
            </View>
        </View>
    );  
}
const styles = StyleSheet.create({
    floatingButton: {
        position: 'absolute',
        width: 65,
        height: 65,
        alignItems: 'center',
        justifyContent: 'center',
        alignSelf: 'center',
        bottom: 20,
    }
});







// 
            {/* <View style={[commonStyles.container, {flex: 4, backgroundColor: colors.accent}]}>
                <View style={{flex: 2, flexDirection: 'row'}}>
                    <View style={{flex: 1, flexDirection: 'column'}}>
                        <Link href="/food/addFood" style={{flex: 1, margin: 1, backgroundColor: 'blue'}}>
                            <View>
                                <Text style={[commonStyles.title, commonStyles.centerText]}>+ {'\n'} Add Food</Text>
                            </View>
                        </Link>
                        <Link href="/food/createRecipe" style={{flex: 1, margin: 1, backgroundColor: 'green'}}>
                            <View>
                                <Text style={[commonStyles.title, commonStyles.centerText]}>Create Recipe</Text>
                            </View>
                        </Link>
                    </View>
                    <View style={{flex: 1, flexDirection: 'column'}}>
                        <Link href="/food/barcodeScan" style={{flex: 1, margin: 1, backgroundColor: 'red'}}>
                            <View>
                                <Text style={[commonStyles.title, commonStyles.centerText]}>Scan Barcode</Text>
                            </View>
                        </Link>
                        <Link href="/food/addFavFood" style={{flex: 1, margin: 1, backgroundColor: 'purple'}}>
                            <View>
                                <Text style={[commonStyles.title, commonStyles.centerText]}>Add from Favourites</Text>
                            </View>
                        </Link>
                    </View>
                </View>
            </View> */}