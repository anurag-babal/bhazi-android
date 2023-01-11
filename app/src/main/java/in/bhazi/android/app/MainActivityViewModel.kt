package `in`.bhazi.android.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*

class MainActivityViewModel : ViewModel() {
    val uiState: StateFlow<MainActivityUiState> = flow<MainActivityUiState> {
        emit(MainActivityUiState.Success)
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainActivityUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000)
    )
}

sealed interface MainActivityUiState {
    object Loading: MainActivityUiState
    object Success: MainActivityUiState
}