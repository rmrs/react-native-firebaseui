import PropTypes from 'prop-types';
import { requireNativeComponent, Image } from 'react-native';

var iface = {
  name: 'FirebaseImage',
  propTypes: {
    ...Image.propTypes,
    path: PropTypes.string,
    resizeMode: PropTypes.oneOf(['cover', 'contain', 'stretch']),
  },
};

module.exports = requireNativeComponent('RCTFirebaseImageView', iface);
