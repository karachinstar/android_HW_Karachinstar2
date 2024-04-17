package ru.gb.android.lession.m19_locations

import android.annotation.SuppressLint
import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationServices
import org.osmdroid.api.IGeoPoint
import org.osmdroid.tileprovider.tilesource.ITileSource
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.compass.CompassOverlay
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class MainViewModel(private val application: Application) : ViewModel() {

    val centreLocation: LiveData<GeoPoint> get() = _centreLocation
    private val _zoom = MutableLiveData(17.0)
    val zoom: LiveData<Double> get() = _zoom
    private val _orientation = MutableLiveData(0f)
    val orientation: LiveData<Float> get() = _orientation
    private val _tile = MutableLiveData<ITileSource>(TileSourceFactory.DEFAULT_TILE_SOURCE)
    val tile: LiveData<ITileSource> get() = _tile
    private var startPoint: GeoPoint = BAIKAL
    private val _centreLocation: MutableLiveData<GeoPoint> = MutableLiveData(startPoint)
    private var _myLocation: MyLocationNewOverlay? = null
    val myLocation get() = _myLocation!!


    init {
        getLastLocation()
    }

    fun setCentreLocation(location: IGeoPoint) {
        _centreLocation.value = GeoPoint(location)
    }

    fun setZoom(zoom: Double) {
        _zoom.value = zoom
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        val fusedLocationProvider = LocationServices.getFusedLocationProviderClient(application)
        fusedLocationProvider.lastLocation.addOnSuccessListener { location ->
            _centreLocation.value = GeoPoint(location.latitude, location.longitude)
        }
    }

    fun setTile(tileSource: ITileSource?) {
        tileSource.let { _tile.value = it }
    }

    fun setOrientation(mapOrientation: Float) {
        _orientation.value = mapOrientation
    }

    fun setMarkers(mapView: MapView) {
        mapMarkerPoints.forEach { pair ->
            val marker = Marker(mapView)
            marker.apply {
                position = pair.value
                icon = ContextCompat.getDrawable(application, R.drawable.baseline_place_24)
                title = pair.key
                setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            }
            mapView.overlays.add(marker)
        }
        mapView.invalidate()
    }

    fun setOptionsForMap(mapView: MapView) {
        val compassOverlay = CompassOverlay(
            application,
            InternalCompassOrientationProvider(application),
            mapView
        )
        compassOverlay.enableCompass()
        _myLocation = MyLocationNewOverlay(GpsMyLocationProvider(application), mapView)
        myLocation.enableMyLocation()
        val rotationGestureOverlay = RotationGestureOverlay(mapView)
        rotationGestureOverlay.isEnabled
        mapView.setMultiTouchControls(true)
        mapView.overlays.apply {
            add(rotationGestureOverlay)
            add(myLocation)
            add(compassOverlay)
        }
    }

    companion object {
        val BAIKAL = GeoPoint(51.8792, 104.7894)

        const val MONUMENT_BUTIN = "Памятник купцу Бутину"
        const val MONUMENT_VAMPILOV = "Памятник Александру Вампилову"
        const val MONUMENT_GAIDAI = "Памятник Гайдаю и его героям"
        const val FEINBERG_HOUSE = "Дом Файнберга"
        const val LOWER_EMBANKMENT = "Нижняя набережная"


        val mapMarkerPoints = mapOf<String, GeoPoint>(
            MONUMENT_BUTIN to GeoPoint(52.277898, 104.282448),
            MONUMENT_VAMPILOV to GeoPoint(52.278570, 104.281035),
            MONUMENT_GAIDAI to GeoPoint(52.286933, 104.287147),
            FEINBERG_HOUSE to GeoPoint(52.288928, 104.286780),
            LOWER_EMBANKMENT to GeoPoint(52.293581, 104.286167),
        )
    }
}