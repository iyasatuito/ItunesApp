package com.pia.appetiser.test.domain.params

data class SearchItunesParam(val term : String,
                             var country : String,
                             val media : String)