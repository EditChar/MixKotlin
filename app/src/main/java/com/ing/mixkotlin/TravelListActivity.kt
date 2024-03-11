package com.ing.mixkotlin

import java.util.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.ing.mixkotlin.adapter.PlaceAdapter
import com.ing.mixkotlin.databinding.ActivityTravelListBinding
import com.ing.mixkotlin.modal.Place
import com.ing.mixkotlin.roomdb.PlaceDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.ArrayList



class TravelListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTravelListBinding
    private val mDisposable = CompositeDisposable()
    private lateinit var places: ArrayList<Place>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTravelListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        places = ArrayList()

        val db = Room.databaseBuilder(applicationContext, PlaceDatabase::class.java, "Places")
            //.allowMainThreadQueries()
            .build()

        val placeDao = db.placeDao()

        mDisposable.add(placeDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))
    }

    private fun handleResponse(placeList: List<Place>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val placeAdapter = PlaceAdapter(placeList)
        binding.recyclerView.adapter = placeAdapter

    }

    fun onAddButtonClick(view: View) {
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("info", "new")
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable.clear()
    }

}