//
//  WonderEvent.h
//  OthelloOnlineObjC
//
//  Created by Adawat Chanchua on 9/24/2560 BE.
//  Copyright © 2560 Adawat Chanchua. All rights reserved.
//

#ifndef WonderEvent_h
#define WonderEvent_h

#import "WonderEventProtocol.h"

@interface WonderEvent : NSURLProtocol

- (void) dispatch: (NSString*) evt;
- (void) registerEventCallback: (id<WonderEventProtocol>) eventCallback;
- (void) deRegisterEventCallback: (id<WonderEventProtocol>) eventCallback;
- (void) emptyEventListener;

@end

#endif /* WonderEvent_h */
