package ru.gb.android.lession.m20_firebase

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log

import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint

import ru.gb.android.lession.m20_firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val launcher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { map ->
        if (map.values.isNotEmpty() && map.values.all { false }) {
            startLocation()
        } else {
            finish()
        }
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        checkPermission()
        viewModelFactory = ViewModelFactory(application)
        viewModel.setMarkers(binding.map)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.setOptionsForMap(binding.map)
        binding.button.setOnClickListener {
            val myLocation: GeoPoint? = viewModel.myLocation.myLocation
            myLocation?.let {
                binding.map.controller.animateTo(it)
            }
            createNotification()
        }

        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))

        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            Log.d("registration token", it.result)
        }
    }

    override fun onResume() {
        super.onResume()
        observeViewModel(binding, viewModel)
        binding.map.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.apply {
            setTile(binding.map.tileProvider.tileSource)
            setOrientation(binding.map.mapOrientation)
            setCentreLocation(binding.map.mapCenter)
            setZoom(binding.map.zoomLevelDouble)
        }
        viewModel.myLocation.onPause()
        binding.map.onPause()
    }

    private fun observeViewModel(binding: ActivityMainBinding, viewModel: MainViewModel) {
        viewModel.orientation.observe(this) {
            binding.map.mapOrientation = it
        }
        viewModel.zoom.observe(this) {
            binding.map.controller.setZoom(it)
        }
        viewModel.centreLocation.observe(this) {
            binding.map.setExpectedCenter(it)
        }
        viewModel.tile.observe(this) {
            binding.map.setTileSource(it)
        }

    }

    private fun startLocation() {
        Configuration.getInstance().load(
            this,
            PreferenceManager.getDefaultSharedPreferences(this)
        )
    }

    private fun checkPermission() {
        if (REQUIRED_PERMISSIONS.all { permission ->
                ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            }) {
            startLocation()
        } else {
            launcher.launch(REQUIRED_PERMISSIONS)
        }
    }



    private fun createNotification(){

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            PendingIntent.getActivities(this, 0, arrayOf(intent), PendingIntent.FLAG_IMMUTABLE)
        else PendingIntent.getActivities(this, 0, arrayOf(intent), PendingIntent.FLAG_UPDATE_CURRENT)


        val notification = NotificationCompat.Builder(this, MyApp.NOTIFICATION_CHANEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Hello my friend")
            .setContentText("Ты в своей точке положения!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        checkPermission()
        NotificationManagerCompat.from(this)
            .notify(NOTIFICATION_ID, notification)
    }

    companion object {
        private const val NOTIFICATION_ID = 1121
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }
}