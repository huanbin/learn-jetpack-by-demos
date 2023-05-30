package com.example.jetpack_demos.hilt

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(subcomponents = [LoginComponent::class])
interface LoginModule {}
