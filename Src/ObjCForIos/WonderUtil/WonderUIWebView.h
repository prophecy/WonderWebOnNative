//
//  WonderWebView.h
//  OthelloOnlineObjC
//
//  Created by Adawat Chanchua on 9/17/2560 BE.
//  Copyright © 2560 Adawat Chanchua. All rights reserved.
//

#ifndef WonderWebView_h
#define WonderWebView_h

#import <UIKit/UIKit.h>
#import "WonderEvent.h"

@interface WonderUIWebView : UIWebView <UIWebViewDelegate>

- (void) load: (NSString*) fileName;
- (void) sendEvent: (NSString*) evtStr;
- (WonderEvent*) getWonderEvent;

@end

#endif /* WonderWebView_h */
