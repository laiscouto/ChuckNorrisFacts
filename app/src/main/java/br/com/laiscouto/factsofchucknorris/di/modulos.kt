package br.com.laiscouto.factsofchucknorris.di

import br.com.laiscouto.factsofchucknorris.service.repository.RepositoryFacts
import br.com.laiscouto.factsofchucknorris.view.ui.main.viewmodelmain.FactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModulo = module {

    factory {
        RepositoryFacts()
    }
    viewModel{
        FactsViewModel(
            get()
        )
    }
}