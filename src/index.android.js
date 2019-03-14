// On Android, both Image and Photo view are supported,
// and defaultSource is converted to an uri that the
// native side can interpret
import React, { Component } from 'react'
import { requireNativeComponent, Image, Platform } from 'react-native'
import iface from './interface'

const RCTFirebaseImageView = requireNativeComponent(
  'RCTFirebaseImageView',
  iface
)
const RCTFirebasePhotoView = requireNativeComponent(
  'RCTFirebasePhotoView',
  iface
)

class ImageView extends Component {
  render() {
    let { defaultSource, ...otherProps } = this.props
    defaultSource = defaultSource
      ? Image.resolveAssetSource(defaultSource).uri
      : undefined
    return (
      <RCTFirebaseImageView {...otherProps} defaultSource={defaultSource} />
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
