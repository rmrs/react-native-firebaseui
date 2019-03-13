//
//  FirebaseUIImageView.h
//  RNFirebaseUi
//
//  Created by erez on 7/11/17.
//  Copyright © 2017 Rumors. All rights reserved.
//

#import <FirebaseStorage/FirebaseStorage.h>

#import <React/RCTResizeMode.h>

@interface FirebaseImageView : UIImageView

@property (nonatomic, copy) NSString *path;
@property (nonatomic, strong) NSNumber *timestamp;
@property (nonatomic, assign) RCTResizeMode resizeMode;
@property (nonatomic, strong) UIImage *defaultSource;

@end
