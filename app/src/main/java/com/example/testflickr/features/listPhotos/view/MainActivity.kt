package com.example.testflickr.features.listPhotos.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testflickr.R
import com.example.testflickr.databinding.ActivityMainBinding
import com.example.testflickr.entities.responses.PhotoResponse
import com.example.testflickr.features.detailPhoto.view.DetailActivity
import com.example.testflickr.features.listPhotos.contract.ContractInterface
import com.example.testflickr.features.listPhotos.presenter.ListPhotosPresenter
import com.example.testflickr.features.listPhotos.adapter.ListPhotosAdapter
import com.example.testflickr.interfaces.ItemClickListener
import java.math.BigDecimal

class MainActivity : AppCompatActivity(), ContractInterface.View, SearchView.OnQueryTextListener, ItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private var presenter: ListPhotosPresenter? = null
    private lateinit var searchView : SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        presenter = ListPhotosPresenter(this)
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

    override fun showSearchList(data: List<PhotoResponse>) {
        if(data.isEmpty()){
            binding.noResultsLayout.visibility = View.VISIBLE
            binding.thumbnailsList.visibility = View.GONE
        } else{
            binding.thumbnailsList.adapter = ListPhotosAdapter(this, data, this)
            binding.thumbnailsList.layoutManager = LinearLayoutManager(this)
            binding.noResultsLayout.visibility = View.GONE
            binding.thumbnailsList.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = "Search View Hint";

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { presenter?.searchPhotos(it) }
        binding.root.hideKeyboard()
        binding.root.requestFocus()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun View.showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onItemClickListener(item: PhotoResponse) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.PARAM_IMAGE_URL,"https://live.staticflickr.com/${item.server}/${item.id}_${item.secret}.jpg")
        intent.putExtra(DetailActivity.PARAM_IMAGE_TITLE, item.title)
        intent.putExtra(DetailActivity.PARAM_IMAGE_AUTHOR, item.ownerName)
        intent.putExtra(DetailActivity.PARAM_IMAGE_DESCRIPTION, item.description.content)
        intent.putExtra(DetailActivity.PARAM_IMAGE_DATE, item.lastUpdate)
        startActivity(intent)
    }

}