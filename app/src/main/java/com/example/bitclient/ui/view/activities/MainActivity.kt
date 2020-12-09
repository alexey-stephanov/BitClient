package com.example.bitclient.ui.view.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.bitclient.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_BitClient)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        val url = "https://bitbucket.org/site/oauth2/authorize?client_id=cDXfEkaZFs8aSvnVgT"
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }
}