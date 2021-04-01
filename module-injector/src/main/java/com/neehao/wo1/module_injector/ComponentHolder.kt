package com.neehao.wo1.module_injector

interface BaseFeatureDependencies {

    val dependencyHolder: BaseDependencyHolder<out BaseFeatureDependencies>
}

interface BaseFeatureApi

interface ComponentHolder<Api : BaseFeatureApi, Dependencies : BaseFeatureDependencies> {

    var dependencyProvider: (() -> Dependencies)?

    fun get(): Api
}