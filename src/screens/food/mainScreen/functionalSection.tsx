import { StyleSheet, Text, View } from 'react-native';
import { colors, commonStyles } from '../../../styles';

export default function FunctionalSection() {
    return (
        <View style={[commonStyles.container]}>
            <View style={{flex: 2, flexDirection: 'row'}}>
                <View style={{flex: 1, flexDirection: 'column'}}>
                    <View style={{flex: 1, margin: 1, backgroundColor: 'blue'}}>
                        <Text style={[commonStyles.title, commonStyles.centerText]}>+ {'\n'} Add Food</Text>
                    </View>
                    <View style={{flex: 1, margin: 1, backgroundColor: 'green'}}>
                        <Text style={[commonStyles.title, commonStyles.centerText]}>Create Recipe</Text>
                    </View>
                </View>
                <View style={{flex: 1, flexDirection: 'column'}}>
                    <View style={{flex: 1, margin: 1, backgroundColor: 'red'}}>
                        <Text style={[commonStyles.title, commonStyles.centerText]}>Scan Barcode</Text>
                    </View>
                    <View style={{flex: 1, margin: 1, backgroundColor: 'purple'}}>
                        <Text style={[commonStyles.title, commonStyles.centerText]}>Add from Favourites</Text>
                    </View>
                </View>
            </View>
        </View>
    );
}