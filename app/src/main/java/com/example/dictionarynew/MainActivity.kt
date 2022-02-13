package com.example.dictionarynew

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionarynew.interactor.MainInteractor
import com.example.dictionarynew.model.DataModel
import com.example.dictionarynew.utils.network.isOnline
import com.example.dictionarynew.view.BaseActivity
import com.example.dictionarynew.view.MainAdapter
import com.example.dictionarynew.view.SearchDialogFragment
import com.example.dictionarynew.view.description.DescriptionActivity
import com.example.dictionarynew.viewmodel.MainViewModel
import com.example.dictionarynew.viewmodel.convertMeaningsToString
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.loading_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

private const val HISTORY_SEARCH_ACTIVITY_PATH =
    "com.example.dictionarynew.HistoryActivity"
const val HISTORY_SEARCH_ACTIVITY_FEATURE_NAME = "history"

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    override lateinit var model: MainViewModel
//    private lateinit var splitInstallManager: SplitInstallManager

    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

    // Слушатель получает от адаптера необходимые данные и запускает новый экран
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text,
                        convertMeaningsToString(data.meanings),
                        data.meanings[0].imageUrl
                    )
                )
            }
        }

    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(applicationContext)
                if (isNetworkAvailable) {
                    model.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniViewModel()
        initViews()
    }

    private fun iniViewModel() {
        if (main_activity_recyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: MainViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@MainActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        search_fab.setOnClickListener(fabClickListener)
        main_activity_recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        main_activity_recyclerview.adapter = adapter
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
//                val intent1 = Intent().setClassName(packageName, HISTORY_SEARCH_ACTIVITY_PATH)
//                startActivity(intent1)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.menu_history -> {
//                splitInstallManager = SplitInstallManagerFactory.create(applicationContext)
//                val request = SplitInstallRequest
//                    .newBuilder()
//                    .addModule(HISTORY_SEARCH_ACTIVITY_FEATURE_NAME)
//                    .build()
//                splitInstallManager
//                    .startInstall(request)
//                    .addOnSuccessListener {
//                        val intent =
//                            Intent().setClassName(packageName, HISTORY_SEARCH_ACTIVITY_PATH)
//                        startActivity(intent)
//                    }
//                    .addOnFailureListener {
//                        Toast.makeText(
//                            applicationContext,
//                            "Couldn't download feature: " + it.message,
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    override fun showViewWorking() {
//        history_loading_frame_layout.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun showViewLoading(progress: Int?) {
        with(main_loading_layout) {
//            history_loading_frame_layout.visibility = View.VISIBLE

            if (progress != null) {
                progress_bar_horizontal.visibility = View.VISIBLE
                progress_bar_round.visibility = View.GONE
                progress_bar_horizontal.progress = progress
            } else {
                progress_bar_horizontal.visibility = View.GONE
                progress_bar_round.visibility = View.VISIBLE
            }
        }
    }
}
