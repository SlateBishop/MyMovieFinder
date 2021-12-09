package ru.gb.makulin.mymoviefinder.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.gb.makulin.mymoviefinder.R
import ru.gb.makulin.mymoviefinder.view.history.HistoryFragment
import ru.gb.makulin.mymoviefinder.view.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            replaceContainerToFragment(MainFragment.newInstance())
        }
        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionHome -> replaceContainerToFragment(MainFragment.newInstance())
            R.id.actionSettings -> replaceContainerToFragment(SettingsFragment())
            R.id.actionHistory -> replaceContainerToFragment(HistoryFragment())
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceContainerToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}