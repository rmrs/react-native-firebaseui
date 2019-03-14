import { requireNativeComponent } from 'react-native'
import iface from './interface'

const ImageView = requireNativeComponent('RCTFirebaseImageView', iface)
const PhotoView = ImageView //PhotoView is supported only on android

module.exports = { ImageView, PhotoView }
