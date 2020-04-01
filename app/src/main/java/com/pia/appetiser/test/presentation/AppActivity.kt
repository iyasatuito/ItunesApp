package com.pia.appetiser.test.presentation

import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.DisposableContainer

abstract class AppActivity : DaggerAppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    protected val disposableContainer: DisposableContainer get() = compositeDisposable


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

}