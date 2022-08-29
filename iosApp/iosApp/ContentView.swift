import SwiftUI
import shared

struct ContentView: View {

    @ObservedObject var viewModel =
        BreedsListViewModelObservableObject(wrapped: ViewModels().getBreedsListViewModel())
    
	var body: some View {
        let breeds = viewModel.breedsList
        let size = breeds.count
		Text("breeds size is: \(size)")
	}
}
