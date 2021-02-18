package com.example.testflickr.features.listPhotos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testflickr.R
import com.example.testflickr.databinding.ActivityMainBinding
import com.example.testflickr.features.listPhotos.contract.ContractInterface
import com.example.testflickr.features.listPhotos.presenter.ListPhotosPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.view.*
import java.math.BigDecimal

class MainActivity : AppCompatActivity(), ContractInterface.View {

    private lateinit var binding: ActivityMainBinding
    private var presenter: ListPhotosPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        presenter = ListPhotosPresenter(this)
        presenter?.searchPhotos("cat")

        Picasso.get().load("https://live.staticflickr.com/65535/50952655756_4e0c5e0834.jpg").into(binding.image);

        //presenter?.getPhotoUrl("65535", "50952655756")

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showError(text: String) {

    }

    override fun updateButtonEnable() {

    }

    override fun showDialogFragment(transactionsMap: MutableMap<String, MutableMap<String, BigDecimal>>) {

    }

    override fun showTransactionsList(transactionsMap: List<String>) {

    }
}