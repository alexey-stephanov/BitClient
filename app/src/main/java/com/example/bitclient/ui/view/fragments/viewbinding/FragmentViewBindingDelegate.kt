package com.example.bitclient.ui.view.fragments.viewbinding

import android.view.View
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBindingDelegate<Binding : ViewBinding>(
        val viewBindingFactory: (View) -> Binding
) : ReadOnlyProperty<Fragment, Binding> {

    private var binding: Binding? = null
    private val lifecycleObserver = BindingLifecycleObserver()

    @MainThread
    override fun getValue(thisRef: Fragment, property: KProperty<*>): Binding {

        this.binding?.let { return it }

        val view = thisRef.requireView()
        thisRef.viewLifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        return viewBindingFactory(view).also { this.binding = it }
    }

    private inner class BindingLifecycleObserver : DefaultLifecycleObserver {
        @MainThread
        override fun onDestroy(owner: LifecycleOwner) {
            owner.lifecycle.removeObserver(this)
            binding = null
        }
    }
}

fun <T : ViewBinding> viewBinding(viewBindingFactory: (View) -> T) =
        FragmentViewBindingDelegate(viewBindingFactory)