//package com.mnafis.foosballmatches.di
//
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentFactory
//import javax.inject.Inject
//import javax.inject.Provider
//
//class AppFragmentFactory @Inject constructor(
//    private val fragmentProviders: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>
//) : FragmentFactory() {
//
//    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
//        val fragmentClass = Class.forName(className).asSubclass(Fragment::class.java)
//        val provider = fragmentProviders[fragmentClass]
//        ?: throw IllegalArgumentException("No provider for fragment: $fragmentClass")
//        return provider.get()
//    }
//}
