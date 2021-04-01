package com.neehao.wo1.module_injector

import java.lang.ref.WeakReference

class ComponentHolderDelegate<Api : BaseFeatureApi, Dependency : BaseFeatureDependencies, Component : Api>(
        private val componentFactory: (Dependency) -> Component
) : ComponentHolder<Api, Dependency> {

    override var dependencyProvider: (() -> Dependency)? = null

    private var componentWeakReference: WeakReference<Component>? = null

    fun getComponentImpl(): Component {
        var component: Component? = null

        synchronized(this) {
            dependencyProvider?.let { provider ->
                component = componentWeakReference?.get()

                if (component == null) {
                    component = componentFactory(provider())
                    componentWeakReference = WeakReference<Component>(component)
                }
            }
                    ?: throw IllegalStateException("dependencyProvider for component with factory $componentFactory is not initialized")
        }

        return component
                ?: throw IllegalStateException("Component holder with component factory $componentFactory is not initialized")
    }

    override fun get(): Api = getComponentImpl()
}