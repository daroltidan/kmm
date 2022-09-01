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
        NavigationView{
            List(vm.breeds, id: \.self) { breed in
                HStack{
                    AsyncImage(url: URL(string: breed.image)){ image in
                        image.resizable()
                    } placeholder: {
                        ProgressView()
                    }.frame(width: 120, height: 120)

                    Text(breed.name)
                }
            }.navigationBarTitle(Text("Breeds"))
        }.onAppear(perform: vm.fetch)
    }
}

