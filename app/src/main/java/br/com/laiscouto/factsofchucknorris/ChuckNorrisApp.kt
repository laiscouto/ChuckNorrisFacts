package br.com.laiscouto.factsofchucknorris


import android.app.Application
import br.com.laiscouto.factsofchucknorris.di.mainModulo
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin



class ChuckNorrisApp: Application() {

    override fun onCreate(){
        super.onCreate()

        startKoin{
            androidContext(this@ChuckNorrisApp)

            modules(mainModulo)

        }
    }
}