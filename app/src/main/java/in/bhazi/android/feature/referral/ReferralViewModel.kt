package `in`.bhazi.android.feature.referral

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ReferralViewModel @Inject constructor() : ViewModel() {
    val referralUiState: StateFlow<ReferralUiState> = flow<ReferralUiState> {
        emit(ReferralUiState.Loading)
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ReferralUiState.Loading
        )


}