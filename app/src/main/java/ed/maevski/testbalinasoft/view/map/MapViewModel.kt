package ed.maevski.testbalinasoft.view.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MapViewModel : ViewModel() {

    class Factory(

    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
                return MapViewModel(
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}