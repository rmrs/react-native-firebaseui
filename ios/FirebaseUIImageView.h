//
//  FirebaseUIImageView.h
//  RNFirebaseUi
//
//  Created by erez on 7/11/17.
//  Copyright © 2017 Rumors. All rights reserved.
//

#import "Firebase.h"
#import <FirebaseStorageUI/FirebaseStorageUI.h>

#import <React/RCTResizeMode.h>

@interface FirebaseImageView : UIImageView

@property (nonatomic, copy) NSString *path;
@property (nonatomic, assign) RCTResizeMode resizeMode;

@end

