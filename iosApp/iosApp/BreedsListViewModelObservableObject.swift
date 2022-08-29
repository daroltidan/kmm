//
//  BreedsListViewModelObservableObject.swift
//  iosApp
//
//  Created by dandarolti on 29.08.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import Combine
import shared
import SwiftUI

public class BreedsListViewModelObservableObject : ObservableObject {

    var viewModel : BreedsListViewModel
    
    @Published private(set) var breedsList: [BreedsListResponseItem]

    init(wrapped: BreedsListViewModel) {
        viewModel = wrapped
        breedsList = []
    }
    
}
