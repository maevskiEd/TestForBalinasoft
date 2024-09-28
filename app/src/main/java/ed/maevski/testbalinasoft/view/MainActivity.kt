package ed.maevski.testbalinasoft.view

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.navigation.NavigationView
import dagger.android.AndroidInjection
import ed.maevski.testbalinasoft.R
import ed.maevski.testbalinasoft.databinding.ActivityMainBinding
import ed.maevski.testbalinasoft.domain.models.Image
import ed.maevski.testbalinasoft.view.auth.AuthFragment
import ed.maevski.testbalinasoft.view.photos.PhotosFragment
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var imageUri: Uri
    lateinit var image: Image

    private val fragmentsWithoutSwitch = listOf(
        R.id.imageDetailFragment,
    )

    private val navController by lazy {
        NavHostFragment.findNavController(supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment)
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainActivityViewModel: MainActivityViewModel
    @Inject
    lateinit var vmFactory: MainActivityViewModel.Factory

    private val launcherGetImageFromCamera =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { succeed ->

            println("launcherGetImageFromCamera: $succeed")

            if (succeed) {

                println("launcherGetImageFromCamera: $imageUri")
                imageUri.let {
                    println("launcherGetImageFromCamera: $image")
//                    mainActivityViewModel.saveImageToDb(image)
                    mainActivityViewModel.upload(image)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainActivityViewModel =
            ViewModelProvider(this, vmFactory)[MainActivityViewModel::class.java]

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->

            getLastLocation()

        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
//        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_photos, R.id.nav_map
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        initShowOrHideSwitch()

        lifecycleScope.launch {
            mainActivityViewModel.userName.collect {userName ->
                val headerView = binding.navView.getHeaderView(0)
                val textView: TextView = headerView.findViewById(R.id.textView)
                textView.text = userName
            }
        }

        lifecycleScope.launch {
            mainActivityViewModel.uriString.collect {

                println("mainActivityViewModel.uriString.collect it = $it")

                val bundle = Bundle()
                bundle.putString("file_uri", it)
                navController.navigate(R.id.imageDetailFragment, bundle)

            }
        }

        mainActivityViewModel.getUserName()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun getImageFromCamera(lat: Double? = null, lng: Double? = null) {
        val file = File.createTempFile("${UUID.randomUUID()}", null, null).apply { deleteOnExit() }
        imageUri = FileProvider.getUriForFile(
            this,
            this.packageName + ".fileprovider",
            file
        )
        val imageFile = file
        image = Image(
            uri = imageUri,
            date = System.currentTimeMillis(),
            lat = lat,
            lng = lng
        )
        launcherGetImageFromCamera.launch(imageUri)

    }

    private fun getLastLocation() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        // Координаты успешно получены
                        val latitude = it.latitude
                        val longitude = it.longitude
                        Log.d("MainActivity", "Широта: $latitude, Долгота: $longitude")
                        getImageFromCamera(latitude, longitude)
                    } ?: run {
                        Log.e("MainActivity", "Не удалось получить местоположение")
                        getImageFromCamera()
                    }
                }
        } else {
            // Запрашиваем разрешения у пользователя
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getLastLocation()
            } else {
                Log.e("MainActivity", "Разрешение на доступ к местоположению не предоставлено")
            }
        }
    }

    private fun initShowOrHideSwitch() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                navController.addOnDestinationChangedListener { _, destination, _ ->
                    with(binding) {
                        if (fragmentsWithoutSwitch.contains(destination.id)) {
                            appBarMain.fab.hide()
                        } else {
                            appBarMain.fab.show()
                        }
                    }
                }
            }
        }
    }

    companion object {
        private val LOCATION_PERMISSION_REQUEST_CODE = 423
    }

}