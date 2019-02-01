# react-native-unlock-device

## Getting started

`$ npm install react-native-react-native-unlock-device --save`

### Mostly automatic installation

`$ react-native link react-native-react-native-unlock-device`

### Manual installation

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`

- Add `import com.reactlibrary.RNUnlockDevicePackage;` to the imports at the top of the file
- Add `new RNUnlockDevicePackage()` to the list returned by the `getPackages()` method

2. Append the following lines to `android/settings.gradle`:

```
include ':react-native-react-native-unlock-device'
project(':react-native-react-native-unlock-device').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-react-native-unlock-device/android')
```

3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:

```
	compile project(':react-native-react-native-unlock-device')
```

Add this this `AndroidManifest.xml`

```xml
<uses-permission android:name="android.permission.WAKE_LOCK" />
<uses-permission android:name="android.permission.DISABLE_KEYGUARD"/>
```

## Usage

```javascript
import RNUnlockDevice from 'react-native-unlock-device';

// TODO: What to do with the module?
RNUnlockDevice.unlock()
  .then(() => console.log('Success'))
  .catch(error => console.log('Error', error));
```
