/**
 * @format
 */

import React, {Component} from 'react';
import {StyleSheet, View} from 'react-native';
import {ImageView, PhotoView} from 'react-native-firebaseui';

//no zoom support
class MyFirebaseImageView extends Component {
  render() {
    let imageProps = this.props;

    return (
      <ImageView
        {...imageProps}
        path="images/firebase_logo.png"
        defaultSource={require('./assets/placeholder.png')}
        timestamp={1000} //optional, can be used to specify last modified time for same storage path
        resizeMode="cover" //'cover', 'contain', 'stretch', 'center'
      />
    );
  }
}

//zoom support (android only). On iOS just wrap the ImageView with a scroll view
class MyFirebasePhotoView extends Component {
  render() {
    let imageProps = this.props;

    return (
      <PhotoView
        {...imageProps}
        path="images/firebase_logo.png"
        defaultSource={require('./assets/placeholder.png')}
        timestamp={1000} //optional, can be used to specify last modified time for same storage path
        resizeMode="cover" //'cover', 'contain', 'stretch', 'center'
      />
    );
  }
}

export default class App extends Component {
  render() {
    return (
      <View style={styles.container}>
        <MyFirebaseImageView style={styles.image} />
        <MyFirebasePhotoView style={styles.image} />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'black',
  },
  image: {
    width: 100,
    height: 100,
    borderRadius: 10,
  },
});
