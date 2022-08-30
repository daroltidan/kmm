import SwiftUI
import shared

class DobgBreedsViewModel: ObservableObject {
    @Published var breeds = [BreedDTO]()    
    private let useCase: GetAllBreeds
    
    init(useCase: GetAllBreeds) {
        self.useCase = useCase
    }

    func getAllBreeds() {
        useCase.getAllBreeds(success: { data in
            self.breeds = data
        })
    }
}

struct ContentView: View {
    @ObservedObject var vm =
    DobgBreedsViewModel(useCase: IOSInjectables().getBreedsUseCase())

	var body: some View {
        Text("there are : \(vm.breeds.count) dog breeds")
            .onAppear(perform: { self.vm.getAllBreeds() })
    }
}
