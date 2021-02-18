package com.example.testflickr.interfaces

import com.example.testflickr.entities.responses.PhotoResponse

interface ItemClickListener {
    fun onItemClickListener(item: PhotoResponse)
}