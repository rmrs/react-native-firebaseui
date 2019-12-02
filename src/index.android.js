// On Android, both Image and Photo view are supported,
// and defaultSource is converted to a uri that the
// native side can interpret
import React, { Component } from 'react'
import { requireNativeComponent, Image, View, StyleSheet } from 'react-native'
import iface from './interface'

const RCTFirebaseImageView = requireNativeComponent(
  'RCTFirebaseImageView',
  iface
)
const RCTFirebasePhotoView = requireNativeComponent(
  'RCTFirebasePhotoView',
  iface
)

const styles = StyleSheet.create({
  imageContainer: {
      overflow: 'hidden',
  },
});

class ImageView extends Component {
  render() {
    let { source, style, defaultSource, resizeMode, ...otherProps } = this.props
    defaultSource = defaultSource
      ? Image.resolveAssetSource(defaultSource).uri
      : undefined
    resizeMode = resizeMode || (style ? style.resizeMode : undefined) || 'cover';
    return (
      <View style={[styles.imageContainer, style]}>
        <RCTFirebaseImageView
          style={StyleSheet.absoluteFill}
          defaultSource={defaultSource}
          resizeMode={resizeMode}
          {...otherProps} />
      </View>
    )
  }
}

class PhotoView extends Component {
  render() {
    let { defaultSource, ...otherProps } = this.props
    defaultSource = defaultSource
      ? Image.resolveAssetSource(defaultSource).uri
      : undefined
    return (
      <RCTFirebasePhotoView {...otherProps} defaultSource={defaultSource} />
    )
  }
}

module.exports = { ImageView, PhotoView }
