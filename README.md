to run use - npx expo start --tunnel --clear
to download as apk - eas build -p android --profile preview --clear-cache

scan mobile logs for errors - adb logcat | Select-String -Pattern "FATAL EXCEPTION"