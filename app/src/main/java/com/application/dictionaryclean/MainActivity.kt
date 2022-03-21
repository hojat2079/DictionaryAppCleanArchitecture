package com.application.dictionaryclean

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.application.dictionaryclean.core.component.WordInfoItem
import com.application.dictionaryclean.feature.dictionary.presentation.TextInfosViewModel
import com.application.dictionaryclean.feature.dictionary.presentation.ui.theme.DictionaryCleanTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryCleanTheme {
                val viewModel: TextInfosViewModel= hiltViewModel()
                val state = viewModel.state.value
                val scaffoldState = rememberScaffoldState()
                LaunchedEffect(key1 = true) {
                    viewModel.eventState.collectLatest { event ->
                        when (event) {
                            is TextInfosViewModel.UIEvent.ShowSnackbar -> {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    event.message,
                                )
                            }
                        }
                    }
                }

                Scaffold(scaffoldState = scaffoldState) {
                    Box(
                        modifier = Modifier
                            .background(
                                MaterialTheme.colors.background
                            )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(all = 16.dp)
                        ) {
                            TextField(
                                value = viewModel.query.value,
                                onValueChange = viewModel::onSearch,
                                placeholder = {
                                    Text(text = "search..")
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(count = state.words.size) { index ->
                                    val wordInfo = state.words[index]
                                    if (index > 0)
                                        Spacer(modifier = Modifier.height(8.dp))
                                    WordInfoItem(wordInfo = wordInfo)
                                    if (index < state.words.size - 1)
                                        Divider()
                                }
                            }
                        }
                        if (state.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }
            }
        }
    }
}