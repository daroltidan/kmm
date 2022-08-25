import SwiftUI
import shared

struct ContentView: View {
    let greet = GreetingHelper().greet()

	var body: some View {
		Text(greet)
	}
}
