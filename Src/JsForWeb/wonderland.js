
// ---------------------------------------------------------------------------
// ---------------------------------------------------------------------------
// Wonderland LIB

var wonderland = (function() {

    // ---------------------------------------------------------------------------
    // Platform data

    var platformSetting = {
        "name": "",
    };

    var PLATFORM_NAME_IOS = "ios";
    var PLATFORM_NAME_ANDROID = "android";

    // ---------------------------------------------------------------------------
    // Platform specific functions

    // Android 
    var TAG_ANDROID = "WonderlandWeb";
    
    var logDebugAndroid = function(msg) {

        wonderEvent.log(TYPE_DEBUG, TAG_ANDROID, msg);
    }

    var sendEventAndroid = function(evt) {

        wonderEvent.onEvent(evt);
    }

    // iOS
    var logDebugIos = function(msg) {

        var xhr = new XMLHttpRequest();
        xhr.open('GET', "http://log_debug/" + encodeURIComponent(msg));
        xhr.send(null);
    }

    var sendEventIos = function(evt) {

        var xhr = new XMLHttpRequest();
        xhr.open('GET', "http://wonder_event/" + encodeURIComponent(evt));
        xhr.send(null);
    }

    // ---------------------------------------------------------------------------
    // Log

    var logDebug = function(msg) {

        try {
            
            if (platformSetting.name === "ios")
                logDebugIos(msg);
            else if (platformSetting.name === "android")
                logDebugAndroid(msg);
            else
                console.log(msg);
        }
        catch (e) {
            logDebug("Exception: " + e.message);
        }
    }

    // ---------------------------------------------------------------------------
    // Event registration
    var eventSet = {};

    var register = function(eventListener) {

        eventSet[eventListener] = eventListener;
    }

    var deRegister = function(eventListener) {

        eventSet[eventListener] = undefined;
    }

    var dispatch = function(evt) {

        try {
            
            for (const key of Object.keys(eventSet))
                eventSet[key](evt);
        }
        catch (e) {
            logDebug("Exception: " + e.message);
        }
    }

    // ---------------------------------------------------------------------------
    // Initialize
    var init = function(platformName, setting) {

        try {

            var settingObject = {};

            if (platformName === PLATFORM_NAME_IOS)
                settingObject = JSON.parse(setting);
            else
                settingObject = setting;

            // Set platform data
            for (var key in platformSetting) {

                if (platformSetting[key] != null) {
                    platformSetting[key] = settingObject[key];
                }
            }    
        }
        catch (e) {
            logDebug("Exception: " + e.message);
        }
    }

    // ---------------------------------------------------------------------------
    // Event handler

    var onEvent = function(evt) {
        
        try {

            // Dispatch client data
            dispatch(evt);
        }
        catch (e) {
            logDebug("Exception: " + e.message);
        }
    }

    var sendEvent = function(evt) {

        try {

            var evtStr = "";

            if (typeof evt !== "string")
                evtStr = JSON.stringify(evt);
            else
                evtStr = evt;

            if (platformSetting.name === "ios")
                sendEventIos(evtStr);
            else if (platformSetting.name === "android")
                sendEventAndroid(evtStr);
            else
                console.log("sendEvent on unsupported platform.");
        }
        catch (e) {
            logDebug("Exception: " + e.message);
        }
    }

    // ---------------------------------------------------------------------------
    // Export functions
    return {

        init: init,

        logDebug: logDebug,

        register: register,
        deRegister: deRegister,

        onEvent: onEvent,
        sendEvent: sendEvent
    }
})();
    