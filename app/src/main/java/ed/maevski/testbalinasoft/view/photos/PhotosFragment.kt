package ed.maevski.testbalinasoft.view.photos

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import ed.maevski.testbalinasoft.R
import ed.maevski.testbalinasoft.databinding.FragmentPhotosBinding
import ed.maevski.testbalinasoft.dialogs.ConfirmationDialogBuilder
import ed.maevski.testbalinasoft.dialogs.ConfirmationDialogListener
import ed.maevski.testbalinasoft.dialogs.DialogType
import ed.maevski.testbalinasoft.domain.models.Image
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotosFragment : Fragment() {
    private val imageAdapter = ImageAdapter() { id, type ->

        val bundle = Bundle()
        bundle.putInt("id_image", id)

        println("Bundle id_image = $id")

        when (type) {
            "click" -> findNavController().navigate(R.id.imageDetailFragment, bundle)

            "longclick" -> {
                showDialog(id)
//                ConfirmationDialogBuilder(object : ConfirmationDialogListener {
//                    override fun onAcceptClick(args: Bundle?) {
//                        viewModel.delImage(id)
//                    }
//                }).setDialogType(DialogType.CONFIRM_DELETE).build()
            }

            else -> println("не click и не longclick")
        }
    }

    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PhotosViewModel

    @Inject
    lateinit var vmFactory: PhotosViewModel.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotosBinding.inflate(inflater, container, false)
        println("onCreateView PhotosFragment")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("onViewCreated PhotosFragment")

        viewModel =
            ViewModelProvider(this, vmFactory)[PhotosViewModel::class.java]

        binding.rvImages.adapter = imageAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.images.collect {

                println("images collect $it")
                initRV(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.idImage.collect {

                println("removeImageById $it")

                imageAdapter.removeImageById(it)
            }
        }
        viewModel.getImages()
    }

    private fun initRV(data: List<Image>) {
        imageAdapter.setData(data)
    }

    fun updateRV() {
        println("updateRV")
        viewModel.getImages()
    }

    private fun showDialog(id: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Удалить фото:")
        builder.setMessage("Вы уверены?")

        builder.setPositiveButton("Да") { dialog, which ->
            viewModel.delImage(id)
        }

        builder.setNegativeButton("Нет") { dialog, which ->
            // Действие при нажатии "Нет"
        }

        builder.show()
    }
}