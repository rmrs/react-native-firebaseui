//
//  FirebaseUIImageView.h
//  RNFirebaseUi
//
//  Created by erez on 7/11/17.
//  Copyright © 2017 Rumors. All rights reserved.
//


#import "FirebaseUIImageView.h"
#import "Firebase.h"
#import <FirebaseStorageUI/FirebaseStorageUI.h>

@implementation FirebaseImageView

- (void)setPath:(NSString *)path
{
    _path = path;
    
    // Reference to an image file in Firebase Storage
    FIRStorageReference *reference = [[FIRStorage storage] referenceWithPath:_path];
    
    // Placeholder image
    UIImage *placeholderImage;
    
    // Load the image using SDWebImage
    [self sd_setImageWithStorageReference:reference placeholderImage:placeholderImage];
}

- (void)setResizeMode:(RCTResizeMode)resizeMode
{
    if (_resizeMode != resizeMode) {
        _resizeMode = resizeMode;
        
        if (_resizeMode == RCTResizeModeRepeat) {
            // Repeat resize mode is handled by the UIImage. Use scale to fill
            // so the repeated image fills the UIImageView.
            self.contentMode = UIViewContentModeScaleToFill;
        } else {
            UIViewContentMode contentMode = (UIViewContentMode)resizeMode;
            self.contentMode = contentMode;
            self.clipsToBounds = true;
        }
    }
}

@end
