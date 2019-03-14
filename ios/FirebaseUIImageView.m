//
//  FirebaseUIImageView.h
//  RNFirebaseUi
//
//  Created by erez on 7/11/17.
//  Copyright © 2017 Rumors. All rights reserved.
//


#import "FirebaseUIImageView.h"
#import <FirebaseStorage/FirebaseStorage.h>
#import "UIImageView+FirebaseStorage.h"

@implementation FirebaseImageView
{
    BOOL _needsReload;
}

- (void)setPath:(NSString *)path
{
    _path = path;
    
    _needsReload = YES;
}

- (void)setDefaultSource:(UIImage *)defaultSource
{
    _defaultSource = defaultSource;
    
    _needsReload = YES;
}

-(void)setTimestamp:(NSNumber*)timestamp
{
    _timestamp = timestamp;
    
    _needsReload = YES;
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

- (void)didSetProps:(NSArray<NSString *> *)changedProps
{
    if (_needsReload) {
        [self reloadImage];
    }
}

-(void)reloadImage
{
    _needsReload = NO;
    // Reference to an image file in Firebase Storage
    FIRStorageReference *reference = [[FIRStorage storage] referenceWithPath:_path];
    
    // Load the image using SDWebImage
    NSString* timestamp = [_timestamp stringValue];
    [self sd_setImageWithStorageReference:reference placeholderImage:_defaultSource customKey:timestamp];
}

@end
