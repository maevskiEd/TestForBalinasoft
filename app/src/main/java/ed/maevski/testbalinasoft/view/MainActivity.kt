package ed.maevski.testbalinasoft.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.FileProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import ed.maevski.testbalinasoft.R
import ed.maevski.testbalinasoft.databinding.ActivityMainBinding
import ed.maevski.testbalinasoft.domain.models.Photo
import java.io.File
import java.util.UUID

class MainActivity : AppCompatActivity() {

    lateinit var imageUri: Uri

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var binding: ActivityMainBinding

    private val launcherGetImageFromCamera =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { succeed ->

            println("launcherGetImageFromCamera: $succeed")

            if (succeed) {
                println("launcherGetImageFromCamera: $imageUri")
                imageUri?.let {
                    val x = binding.imgPhoto.setImageURI(it)
                    Photo(image = it.path.toString(),date = System.currentTimeMillis())
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->

            getImageFromCamera()

        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_photos, R.id.nav_map
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun getImageFromCamera() {
        val file = File.createTempFile("${UUID.randomUUID()}", null, null).apply { deleteOnExit() }
        imageUri = FileProvider.getUriForFile(
            this,
            this.packageName + ".fileprovider",
            file
        )
        val imageFile = file
        launcherGetImageFromCamera.launch(imageUri)
    }
}