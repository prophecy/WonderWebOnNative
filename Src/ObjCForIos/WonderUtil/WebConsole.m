//
//  WebConsole.m
//  OthelloOnlineObjC
//
//  Created by Adawat Chanchua on 9/24/2560 BE.
//  Copyright © 2560 Adawat Chanchua. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "WebConsole.h"

@interface WebConsole()

@end

static WonderEvent* _s_wonderEvent;

@implementation WebConsole

+ (void) enable {
    
    [NSURLProtocol registerClass:[WebConsole class]];
    
    _s_wonderEvent = [[WonderEvent alloc] init];
}

+ (BOOL) canInitWithRequest:(NSURLRequest *)request {
    
    if ([[[request URL] host] isEqualToString:@"log_debug"]){
        
        NSLog(@"%@", [[[request URL] path] substringFromIndex: 1]);
    }
    else if ([[[request URL] host] isEqualToString:@"wonder_event"]){
        
        NSString* evtStr = [[[request URL] path] substringFromIndex: 1];
        [_s_wonderEvent dispatch:evtStr];
    }
    
    return NO;
}

+ (WonderEvent*) getWonderEvent {
 
    return _s_wonderEvent;
}

@end
