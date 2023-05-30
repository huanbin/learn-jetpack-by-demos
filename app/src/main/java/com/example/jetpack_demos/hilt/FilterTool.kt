package com.example.jetpack_demos.hilt

class FilterTool(var filters: MutableList<Filter>) {
    fun addFilter(filter: Filter) {
        this.filters.add(filter)
    }
}