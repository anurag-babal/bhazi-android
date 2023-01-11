package `in`.bhazi.android.feature.referral

import `in`.bhazi.android.core.model.Referral

sealed interface ReferralUiState {
    object Loading : ReferralUiState
    data class Success(val referral: Referral) : ReferralUiState
}