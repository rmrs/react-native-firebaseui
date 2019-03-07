require 'json'
version = JSON.parse(File.read('../package.json'))["version"]

Pod::Spec.new do |s|
  s.name         = "RNFirebaseUi"
  s.version      = version
  s.summary      = "RNFirebaseUi"
  s.description  = <<-DESC
                  React Native Firebase Bindings Based on FirebaseUI SDK
                   DESC
  s.homepage     = "https://github.com/rmrs/react-native-firebaseui#readme"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "erezrokah@gmail.com" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNFirebaseUi.git", :tag => "master" }
  s.source_files  = "RNFirebaseUi/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  s.dependency 'FirebaseUI/Storage'
end
