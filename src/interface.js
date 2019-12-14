import { Image } from 'react-native';
import PropTypes from 'prop-types';

module.exports = {
  name: 'FirebaseImage',
  propTypes: {
    ...Image.propTypes,
    path: PropTypes.string,
    timestamp: PropTypes.number,
    resizeMode: PropTypes.oneOf(['cover', 'contain', 'stretch', 'center']),
    defaultSource: PropTypes.number,
  },
};
