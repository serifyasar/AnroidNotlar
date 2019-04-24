package com.serifyasar.fotoslayt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ResimViewModel
    private lateinit var resimler:List<Photo>
    private lateinit var url:String
    var size: Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel= ViewModelProviders.of(this).get(ResimViewModel::class.java)

        viewModel.resimler.observe(this, Observer {

            resimler=it
            url = "https://images.pexels.com/" + resimler[viewModel.index].resim
            Picasso.get().load(url).into(imageView)
            size=it.size

            fab.setOnClickListener {
                viewModel.index=Random().nextInt(size)
                url= "https://images.pexels.com/" + resimler[viewModel.index].resim
                Picasso.get().load(url).into(imageView)
                Toast.makeText(this,viewModel.index.toString(),Toast.LENGTH_SHORT).show()
            }
        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_option,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.menuResim ->  startActivity(Intent(this, ResimEkleActivity::class.java))
            else ->  super.onOptionsItemSelected(item)
        }
        return true

    }
}
