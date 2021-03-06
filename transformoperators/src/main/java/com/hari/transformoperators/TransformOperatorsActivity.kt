package com.hari.transformoperators

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.view_transform_operators.*

/**
 * @author Hari Hara Sudhan.N
 */
class TransformOperatorsActivity : AppCompatActivity(),
    View.OnClickListener {

    private var latitude = 0.0
    private var longitude = 0.0

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, TransformOperatorsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_transform_operators)
        getGeoLocations()
        buffer.setOnClickListener(this)
        map.setOnClickListener(this)
        flatMap.setOnClickListener(this)
        switchMap.setOnClickListener(this)
        groupBy.setOnClickListener(this)
        scan1.setOnClickListener(this)
        scan2.setOnClickListener(this)
        reduce.setOnClickListener(this)
        flatMapMaybe.setOnClickListener(this)
        windowOS.setOnClickListener(this)
        windowOT.setOnClickListener(this)
        windowSkip.setOnClickListener(this)
        cast.setOnClickListener(this)
        flatMapSingle.setOnClickListener(this)
        concatMapEager.setOnClickListener(this)
        concatMapEagerWithError.setOnClickListener(this)
        flattenAsFlowable.setOnClickListener(this)
        flattenAsObservable.setOnClickListener(this)
        flatMapObservable.setOnClickListener(this)
        flatMapSingleElement.setOnClickListener(this)
    }

    private fun getGeoLocations() {
        val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (isGpsEnabled
            && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?:locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            location?.let {
                latitude = location.latitude
                longitude = location.longitude
            }
        }
    }

    private val presenter by lazy {
        TransformOperatorsPresenter(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            buffer -> {
                presenter.buffer()
            }
            map -> {
                presenter.map()
            }
            flatMap -> {
                presenter.flatMap(latitude, longitude)
            }
            flatMapMaybe -> {
                presenter.flatMapMaybe(latitude, longitude)
            }
            switchMap -> {
                presenter.switchMap()
            }
            groupBy -> {
                presenter.groupBy(latitude, longitude)
            }
            scan1 -> {
                presenter.scan1()
            }
            scan2 -> {
                presenter.scan2()
            }
            reduce -> {
                presenter.reduce()
            }
            windowOS -> {
                presenter.windowBasedOnSize()
            }
            windowOT -> {
                presenter.windowBasedOnTime()
            }
            windowSkip -> {
                presenter.windowSkip()
            }
            cast -> {
                presenter.cast()
            }
            flatMapSingle -> {
                presenter.flatMapSingleWithObservable()
            }
            concatMapEager -> {
                presenter.concatMapEager()
            }
            concatMapEagerWithError -> {
                presenter.concatMapEagerDelayError()
            }
            flattenAsFlowable -> {
                presenter.flattenAsFlowable(latitude, longitude)
            }
            flattenAsObservable -> {
                presenter.flattenAsObservable(latitude, longitude)
            }
            flatMapObservable -> {
                presenter.flatMapObservable(latitude, longitude)
            }
            flatMapSingleElement -> {
                presenter.flatMapSingleElement()
            }
        }
    }
}