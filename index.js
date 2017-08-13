import PropTypes from 'prop-types'
import { requireNativeComponent, Image, Platform } from 'react-native'

var iface = {
  name: 'FirebaseImage',
  propTypes: {
    ...Image.propTypes,
    path: PropTypes.string,
    resizeMode: PropTypes.oneOf(['cover', 'contain', 'stretch']),
  },
}

const ImageView = requireNativeComponent('RCTFirebaseImageView', iface)
let PhotoView = null
//PhotoView is supported only on android
if (Platform.OS === 'android') {
  PhotoView = requireNativeComponent('RCTFirebasePhotoView', iface)
} else {
  PhotoView = ImageView
}
module.exports = { ImageView, PhotoView }
