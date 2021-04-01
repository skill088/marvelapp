package com.neehao.wo1.module_injector

interface BaseDependencyHolder<Dependency : BaseFeatureDependencies> {

    val dependencies: Dependency
}

abstract class DependencyHolder0<Dependency : BaseFeatureDependencies> :
        BaseDependencyHolder<Dependency> {

    abstract val block: (BaseDependencyHolder<Dependency>) -> Dependency

    override val dependencies: Dependency
        get() = block(this)
}

abstract class DependencyHolder1<Api1 : BaseFeatureApi, Dependency : BaseFeatureDependencies>(
        private val api1: Api1
) : BaseDependencyHolder<Dependency> {

    abstract val block: (BaseDependencyHolder<Dependency>, Api1) -> Dependency

    override val dependencies: Dependency
        get() = block(this, api1)
}

abstract class DependencyHolder2<Api1 : BaseFeatureApi, Api2 : BaseFeatureApi, Dependency : BaseFeatureDependencies>(
        private val api1: Api1,
        private val api2: Api2
) : BaseDependencyHolder<Dependency> {

    abstract val block: (BaseDependencyHolder<Dependency>, Api1, Api2) -> Dependency

    override val dependencies: Dependency
        get() = block(this, api1, api2)
}