//
//  RNFirebaseImage.m
//  RNFirebaseUi
//
//  Created by erez rokah on 7/11/17.
//  Copyright © 2017 Rumors. All rights reserved.
//


#import "RNFirebaseImage.h"
#import "Firebase.h"
#import <FirebaseStorageUI/FirebaseStorageUI.h>

@implementation RCTFirebaseImageView

RCT_EXPORT_MODULE()

UIImageView *_imageView;

- (UIView *)view
{
    _imageView = [[UIImageView alloc] init];
    
    return _imageView;
}

- (void)setPath:(NSString *)path
{
    _path = path;
    
    // Reference to an image file in Firebase Storage
    FIRStorageReference *reference = [[FIRStorage storage] referenceWithPath:_path];
    
    // Placeholder image
    UIImage *placeholderImage;
    
    // Load the image using SDWebImage
    [_imageView sd_setImageWithStorageReference:reference placeholderImage:placeholderImage];
}

@end
