//
//  WebConsole.h
//  OthelloOnlineObjC
//
//  Created by Adawat Chanchua on 9/24/2560 BE.
//  Copyright © 2560 Adawat Chanchua. All rights reserved.
//

#ifndef WebConsole_h
#define WebConsole_h

#import "WonderEvent.h"

@interface WebConsole : NSURLProtocol

+ (void) enable;
+ (WonderEvent*) getWonderEvent;

@end

#endif /* WebConsole_h */
