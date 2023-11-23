package com.education.brcmeducorn.api.apiModels

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class AddGalleryReq(
    val description: RequestBody,
    val token: RequestBody,
    val tags: RequestBody
)