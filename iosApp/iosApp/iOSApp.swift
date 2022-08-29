import SwiftUI
import shared


@main
struct iOSApp: App {

    init(){
        KoinIOSKt.doInitKoinIos(doOnStartup: { NSLog("iOS app started") })
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
