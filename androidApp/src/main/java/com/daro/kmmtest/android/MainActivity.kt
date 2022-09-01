package com.daro.kmmtest.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.DarkMode
import androidx.compose.material.icons.twotone.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
            val isSystemDarkTheme = isSystemInDarkTheme()
            val enableDarkMode = remember { mutableStateOf(isSystemDarkTheme) }
            val onDarkModeChanged = {
                enableDarkMode.value = !enableDarkMode.value
            }
            KMMAndroidTheme(darkTheme = enableDarkMode.value) {
                MainContent(
                    onDarkModeChanged = onDarkModeChanged,
                    isDarkModeEnabled = isSystemDarkTheme
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun MainContent(
        onDarkModeChanged: () -> Unit = {},
        isDarkModeEnabled: Boolean = false,
    ) {
        val topAppBarScrollState = rememberTopAppBarScrollState()
        val topAppBarScrollBehavior =
            remember { TopAppBarDefaults.enterAlwaysScrollBehavior(state = topAppBarScrollState) }
        Scaffold(
            modifier = Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
            topBar = {
                LargeTopAppBar(
                    title = { Text("Breeds") },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = MaterialTheme.colorScheme.onBackground,
                        actionIconContentColor = MaterialTheme.colorScheme.onBackground
                    ),
                    scrollBehavior = topAppBarScrollBehavior,
                    actions = {
                        IconButton(onClick = onDarkModeChanged) {
                            val icon =
                                if (isDarkModeEnabled) Icons.TwoTone.LightMode else Icons.TwoTone.DarkMode
                            Icon(
                                imageVector = icon,
                                contentDescription = "dark mode change",
                            )
                        }
                    }
                )
            }
        ) { contentPadding ->
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
                            .size(120.dp, 120.dp)
                            .align(alignment = Alignment.CenterVertically)
                            .clip(MaterialTheme.shapes.medium)
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
