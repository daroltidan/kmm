import SwiftUI
import shared

class DobgBreedsViewModel: ObservableObject {
    @Published var breeds = [BreedDTO]()

    
    private let getAllBreedsUsecase: GetAllBreeds
    init(useCase: GetAllBreeds) {
        self.getAllBreedsUsecase = useCase
    }

    func fetch() {
        getAllBreedsUsecase.getAllBreeds(success: { data in
            self.breeds = data
        })
    }
}

struct ContentView: View {
    @ObservedObject var vm =
    DobgBreedsViewModel(useCase: IOSInjectables().getBreedsUseCase())

	var body: some View {
        List(vm.breeds, id: \.self) { breed in
                Text(breed.name)
        }.onAppear(perform: vm.fetch)
    }
}
