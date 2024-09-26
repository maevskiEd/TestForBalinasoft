package ed.maevski.testbalinasoft.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivityViewModel : ViewModel() {

    class Factory(

    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                return MainActivityViewModel(
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}