package com.example.raku.itcapp.views.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.example.raku.itcapp.base.BaseActivity
import com.example.raku.itcapp.components.Event
import com.example.raku.itcapp.views.Navigator
import com.itc.app.R
import com.itc.app.listener.EndlessRecyclerViewScrollListener
import com.itc.app.models.BaseResponseModal
import com.itc.app.models.home.Product
import com.itc.app.models.home.Products
import com.itc.app.views.home.InnerListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var mHomeViewModel: HomeViewModel
    private lateinit var mAdapter: InnerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mHomeViewModel = ViewModelProviders.of(this@MainActivity).get(HomeViewModel::class.java)
        handleObserver()
        handleUI()
    }

    override fun onStart() {
        super.onStart()
        mHomeViewModel.getListRows(1, 15)
    }

    /**
     * Method call to initialize observer
     */
    private fun handleObserver() {
        mHomeViewModel.mldProducts.observe(this, mProductsObserver)
        mHomeViewModel.mldError.observe(this@MainActivity, onErrorObserver)
    }

    /**
     * Method call to handle UI
     */
    private fun handleUI() {
        mAdapter = InnerListAdapter(ArrayList<Product>(), onItemClick)
        var layoutManager:LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_view_home.layoutManager = layoutManager
        recycler_view_home.adapter = mAdapter
        recycler_view_home.addOnScrollListener(object :EndlessRecyclerViewScrollListener(layoutManager){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                mHomeViewModel.getListRows(page, 15)
            }
        })
    }

    /**
     * Item click listener
     */
    var onItemClick = View.OnClickListener {
        println("Clicked...............")
                Navigator.instance.navigateToDetails(this@MainActivity, it.getTag(R.id.product) as Product)
    }


    /**
     * Row data observer
     */
    private val mProductsObserver: Observer<Products> = Observer {
        mAdapter.addProducts((it as Products)!!.products)
    }

    /**
     * Error Observer
     */
    private val onErrorObserver: Observer<Event<BaseResponseModal>> = Observer { event ->
        if (event?.getContentIfNotHandled() != null) {
            Toast.makeText(this@MainActivity, event.peekContent().errorText, Toast.LENGTH_LONG).show()
        }
    }
}
