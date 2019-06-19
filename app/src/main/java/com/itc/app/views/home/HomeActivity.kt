package com.itc.app.views.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.itc.app.R
import com.itc.app.base.BaseActivity
import com.itc.app.components.Event
import com.itc.app.listener.EndlessRecyclerViewScrollListener
import com.itc.app.models.BaseResponseModal
import com.itc.app.models.home.Product
import com.itc.app.models.home.Products
import com.itc.app.views.Navigator
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    private lateinit var mHomeViewModel: HomeViewModel
    private lateinit var mAdapter: HomeListAdapter
    private val PAGE_SIZE = 15
    private var PAGE_NUMBER = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mHomeViewModel = ViewModelProviders.of(this@HomeActivity).get(HomeViewModel::class.java)
        handleObserver()
        handleUI()
        getProducts()
    }

   private fun getProducts(){
       progress_bar.visibility = View.VISIBLE
       mHomeViewModel.getListRows(PAGE_NUMBER, PAGE_SIZE)
       PAGE_NUMBER++
   }

    /**
     * Method call to initialize observer
     */
    private fun handleObserver() {
        mHomeViewModel.mldProducts.observe(this, mProductsObserver)
        mHomeViewModel.mldError.observe(this@HomeActivity, onErrorObserver)
    }

    /**
     * Method call to handle UI
     */
    private fun handleUI() {
        mAdapter = HomeListAdapter(ArrayList<Product>(), onItemClick)
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_view_home.layoutManager = layoutManager
        recycler_view_home.adapter = mAdapter
        recycler_view_home.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                progress_bar.visibility = View.VISIBLE
                mHomeViewModel.getListRows(PAGE_NUMBER, PAGE_SIZE)
                PAGE_NUMBER++
            }
        })
    }

    /**
     * Item click listener
     */
    var onItemClick = View.OnClickListener {
        Navigator.instance.navigateToDetails(this@HomeActivity, it.getTag(R.id.product) as Product)
    }


    /**
     * Row data observer
     */
    private val mProductsObserver: Observer<Products> = Observer {
        mAdapter.addProducts((it as Products)!!.products)
        progress_bar.visibility = View.GONE
    }

    /**
     * Error Observer
     */
    private val onErrorObserver: Observer<Event<BaseResponseModal>> = Observer { event ->
        if (event?.getContentIfNotHandled() != null) {
            Toast.makeText(this@HomeActivity, event.peekContent().errorText, Toast.LENGTH_LONG).show()
        }
    }
}
