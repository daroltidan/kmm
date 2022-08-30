package com.daro.kmmtest.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.daro.kmmtest.android.breeds.BreedsListViewModel
import com.daro.kmmtest.domain.BreedDTO
import com.daro.uicomponents.theme.KMMAndroidTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class MainActivity : ComponentActivity(), KoinComponent {
    private val breedsListViewModel: BreedsListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KMMAndroidTheme {
                MainContent()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun MainContent() {
        Scaffold { contentPadding ->
            Box(modifier = Modifier.padding(contentPadding)) {
                val breeds = breedsListViewModel.breedsList.collectAsState(emptyList()).value
                BreedsListContent(breeds)
            }
        }
    }

    @Composable
    private fun BreedsListContent(breeds: List<BreedDTO>) {
        LazyColumn {
            items(breeds) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = item.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(64.dp, 64.dp)
                            .align(alignment = Alignment.CenterVertically)
                    )
                    Text(
                        text = item.name,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .padding(4.dp),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        }
    }
}
