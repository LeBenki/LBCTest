package com.test.lbc

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse

import okhttp3.mockwebserver.RecordedRequest

class MockServerDispatcher {

    inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/technical-test.json" -> {
                    MockResponse().setResponseCode(200).setBody("""[
                        {
                            "albumId": 1,
                            "id": 1,
                            "title": "baccusamus beatae ad facilis cum similique qui sunt",
                            "url": "https://via.placeholder.com/600/92c952",
                            "thumbnailUrl": "https://via.placeholder.com/150/92c952"
                        },
                        {
                            "albumId": 2,
                            "id": 51,
                            "title": "non sunt voluptatem placeat consequuntur rem incidunt",
                            "url": "https://via.placeholder.com/600/8e973b",
                            "thumbnailUrl": "https://via.placeholder.com/150/8e973b"
                        },
                        {
                            "albumId": 3,
                            "id": 101,
                            "title": "aincidunt alias vel enim",
                            "url": "https://via.placeholder.com/600/e743b",
                            "thumbnailUrl": "https://via.placeholder.com/150/e743b"
                        }]""")
                }
                else -> MockResponse().setResponseCode(404)
            }
        }
    }
}