
Pod::Spec.new do |s|
  s.name         = "RNFirebaseUi"
  s.version      = "v0.0.1-alpha1"
  s.summary      = "RNFirebaseUi"
  s.description  = <<-DESC
                  React Native Firebase Bindings Based on FirebaseUI SDK
                   DESC
  s.homepage     = "https://github.com/rmrs/react-native-firebaseui#readme"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "erez@rumors.io" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNFirebaseUi.git", :tag => "master" }
  s.source_files  = "RNFirebaseUi/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  s.dependency 'FirebaseUI/Storage', '~> 4.0'
end
