package com.ing.mixkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ing.mixkotlin.databinding.ActivityMainBinding
import com.ing.mixkotlin.modal.Place
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.lang.Exception
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mDisposable = CompositeDisposable()
    private lateinit var places: ArrayList<Place>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



    }

    fun onArtListButtonClick(view: View) {
        val intent = Intent(this, ArtListActivity::class.java)
        startActivity(intent)
    }

    fun onTravelListButtonClick(view: View) {
        val intent = Intent(this, TravelListActivity::class.java)
        startActivity(intent)
    }


}
