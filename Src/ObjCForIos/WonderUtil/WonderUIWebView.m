//
//  WonderWebView.m
//  OthelloOnlineObjC
//
//  Created by Adawat Chanchua on 9/17/2560 BE.
//  Copyright © 2560 Adawat Chanchua. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "WonderUIWebView.h"
#import "WebConsole.h"

@interface WonderUIWebView ()

- (void) initWebView;
- (void) initPlatformSetting;

@end

@implementation WonderUIWebView

- (id) init
{
    self = [super init];
    
    if (self != nil) {
        
        [self initWebView];
    }
    return self;
}

- (instancetype)initWithFrame:(CGRect)aRect {
    
    self = [super initWithFrame:aRect];
    
    if (self != nil) {
        
        [self initWebView];
    }
    return self;
}

- (nullable instancetype)initWithCoder:(NSCoder *)aDecoder {
    
    self = [super initWithCoder:aDecoder];
    
    if (self != nil) {
        
        [self initWebView];
    }
    return self;
}

- (void) initWebView {
    
    // Enable console debug log
    [WebConsole enable];
    
    self.delegate = self;
}

- (void) load: (NSString*) fileName {
    
    // Extract name and extension
    NSArray* nameSplit = [fileName componentsSeparatedByString:@"."];
    NSString* name = @"";
    NSString* extension = @"";
    
    if (nameSplit.count >= 1)
        name = nameSplit[0];
    if (nameSplit.count >= 2)
        extension = nameSplit[1];
    
    // Start load
    NSString *htmlFile = [[NSBundle mainBundle] pathForResource:name ofType:extension];
    NSString* htmlString = [NSString stringWithContentsOfFile:htmlFile encoding:NSUTF8StringEncoding error:nil];
    [self loadHTMLString:htmlString baseURL: [[NSBundle mainBundle] bundleURL]];
}

- (void)webViewDidFinishLoad:(UIWebView *)webView
{
    [self initPlatformSetting];
}

- (void)initPlatformSetting {
    
    // Specify platform
    NSDictionary* platformDataEvt = [[NSDictionary alloc] initWithObjectsAndKeys:
                                     @"ios", @"name",
                                     nil];
    
    // Convert object to json data
    NSError *jsonError = nil;
    NSData* jsonData = [NSJSONSerialization dataWithJSONObject:platformDataEvt options:kNilOptions error:&jsonError];
    
    // Convert data to string
    NSString* jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    
    // Compose JS function call
    NSString* dstFunctionCall = [NSString stringWithFormat:@"%@%@%@", @"wonderland.init('ios','", jsonString, @"')"];
    
    // Send event to webview
    [self stringByEvaluatingJavaScriptFromString:dstFunctionCall];
}

- (void) sendEvent: (NSString*) evtStr {
    
    // Compose JS function call
    NSString* dstFunctionCall = [NSString stringWithFormat:@"%@%@%@", @"wonderland.onEvent('", evtStr, @"')"];
    
    // Send event to webview
    [self stringByEvaluatingJavaScriptFromString:dstFunctionCall];
}

- (WonderEvent*) getWonderEvent {
    
    return [WebConsole getWonderEvent];
}

@end
