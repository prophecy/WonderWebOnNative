//
//  WonderEvent.m
//  OthelloOnlineObjC
//
//  Created by Adawat Chanchua on 9/24/2560 BE.
//  Copyright © 2560 Adawat Chanchua. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "WonderEvent.h"

@interface WonderEvent ()

@property (strong) NSMutableSet* eventCallbackSet;

@end

@implementation WonderEvent

- (id) init
{
    self = [super init];
    
    if (self != nil) {
        
        _eventCallbackSet = [[NSMutableSet alloc] init];
    }
    return self;
}

- (void) dispatch: (NSString*) evt {
    
    for (id obj in _eventCallbackSet) {
        
        id<WonderEventProtocol> eventCallback = (id<WonderEventProtocol>)obj;
        
        [eventCallback onEvent:evt];
    }
}

- (void) registerEventCallback: (id<WonderEventProtocol>) eventCallback {
    
    [_eventCallbackSet addObject:eventCallback];
}

- (void) deRegisterEventCallback: (id<WonderEventProtocol>) eventCallback {
    
    [_eventCallbackSet removeObject:eventCallback];
}

- (void) emptyEventListener {
    
    [_eventCallbackSet removeAllObjects];
}

@end
