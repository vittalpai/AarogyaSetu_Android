package com.healthtrack.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healthtrack.utility.AuthUtility

class OnBoardingViewModel : ViewModel() {
    var signedInState = MutableLiveData(false)
    var whyNeededshown = MutableLiveData(false)

    var isSharingPossible = MutableLiveData(false)

    init {
        signedInState.value = AuthUtility.isSignedIn()
        isSharingPossible.value = true

    }
}
