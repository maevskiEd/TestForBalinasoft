package ed.maevski.testbalinasoft.view.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PhotosViewModel : ViewModel() {

    class Factory(

        ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PhotosViewModel::class.java)) {
                return PhotosViewModel(
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}