package com.ing.mixkotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ing.mixkotlin.databinding.ActivityArtListBinding

class ArtListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtListBinding
    private lateinit var artList : ArrayList<Art>
    private lateinit var artAdapter : ArtAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityArtListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // sql kaydedilen itemleri listede gösterme ui
        artList = ArrayList<Art>()
        artAdapter = ArtAdapter(artList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = artAdapter

        try {

            val database = this.openOrCreateDatabase("Arts", Context.MODE_PRIVATE,null)

            val cursor = database.rawQuery("SELECT * FROM arts",null)
            val artNameIx = cursor.getColumnIndex("artname")
            val idIx = cursor.getColumnIndex("id")

            while (cursor.moveToNext()) {
                val name = cursor.getString(artNameIx)
                val id = cursor.getInt(idIx)
                val art = Art(name,id)
                artList.add(art)
            }

            artAdapter.notifyDataSetChanged() //yeni veri geldi bi kendini yenile

            cursor.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
    fun onAddButtonClick(view: View) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("info", "new")
        startActivity(intent)
    }

}