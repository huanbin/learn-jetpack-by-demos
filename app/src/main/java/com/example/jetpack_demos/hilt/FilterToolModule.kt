package com.example.jetpack_demos.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
class FilterToolModule {

    //绑定作用域
    @ActivityScoped
    //限定符（预赛者）
    @OneFilterTool
    //绑定
    @Provides
    fun provideFilterTool1(): FilterTool {
        val filterTool = FilterTool(mutableListOf(Fileter1()))
        return filterTool
    }

    @TwoFilterTool
    @Provides
    fun provideFilterTool2(): FilterTool {
        val filterTool = FilterTool(mutableListOf(Fileter1()))
        filterTool.addFilter(Fileter2())
        return filterTool
    }
}