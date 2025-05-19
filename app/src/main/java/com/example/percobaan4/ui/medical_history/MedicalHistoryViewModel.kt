//package com.example.percobaan4.ui.medical
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//
//class MedicalHistoryViewModel : ViewModel() {
//
//    private val _text = MutableLiveData<String>().apply {
//        value = "Medical History Information"
//    }
//    val text: LiveData<String> = _text
//}
package com.example.percobaan4.ui.medical

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MedicalHistoryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Health Parameters History"
    }
    val text: LiveData<String> = _text
}