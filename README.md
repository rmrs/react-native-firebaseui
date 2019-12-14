# react-native-firebaseui

## Requirements

We assume you already have firebase sdk installed and configured.
We're using this great library:
[react-native-firebase](https://github.com/invertase/react-native-firebase)

## Getting started

`$ npm install react-native-firebaseui --save`

### Mostly automatic installation

`$ react-native link react-native-firebaseui`

For iOS add the following pod to your podfile:

```pod
pod 'SDWebImage', '~> 4.0'
```

and run pod install.

## Android Additional step for PhotoView Library

Add this in your root build.gradle file (usually under `android/build.gradle`):

```gradle
allprojects {
  repositories {
    ...
    maven { url "https://jitpack.io" }
  }
}
```

### Manual installation

#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-firebase-ui` and add `RNFirebaseUi.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNFirebaseUi.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`

- Add `import io.rumors.reactnativefirebaseui.RNFirebaseUiPackage;` to the imports at the top of the file
- Add `new RNFirebaseUiPackage()` to the list returned by the `getPackages()` method

2. Append the following lines to `android/settings.gradle`:

```gradle
include ':react-native-firebase-ui'
project(':react-native-firebase-ui').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-firebase-ui/android')
```

3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:

```gradle
  compile project(':react-native-firebase-ui')
```

## Usage

```javascript
import { ImageView, PhotoView } from 'react-native-firebaseui';

//no zoom support
export class MyFirebaseImageView extends Component<void, void, void> {
  constructor(props) {
    super(props);
  }

  render() {
    let imageProps = this.props;

    return (
      <ImageView
        {...imageProps}
        path="firebase/storage/path"
        defaultSource={require('./placeholder.png')} // optional, show placeholder until image is loaded
        timestamp={0} //optional, can be used to specify last modified time for same storage path
        resizeMode="cover" //'cover', 'contain', 'stretch', 'center'
      />
    );
  }
}

//zoom support (android only). On iOS just wrap the ImageView with a scroll view
export class MyFirebasePhotoView extends Component<void, void, void> {
  constructor(props) {
    super(props);
  }

  render() {
    let imageProps = this.props;

    return (
      <PhotoView
        {...imageProps}
        path="firebase/storage/path"
        defaultSource={require('./placeholder.png')} // optional, show placeholder until image is loaded
        timestamp={0} //optional, can be used to specify last modified time for same storage path
        resizeMode="cover" //'cover', 'contain', 'stretch', 'center'
      />
    );
  }
}
```

> Note: On Android, the `defaultSource` prop is ignored on debug builds.
