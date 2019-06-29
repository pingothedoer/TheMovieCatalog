package com.pingo.tmdb.app.movies

import androidx.lifecycle.Observer
import com.pingo.tmdb.app.movies.data.MoviesCatalogRepoImp
import com.pingo.tmdb.shared.models.Movie
import com.pingo.tmdb.shared.models.MoviesCatalog
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-27.
 * ---------------------------------------------
 *
 * Testing [MoviesCatalogViewModel]. Mocking [MoviesCatalogRepoImp] to fetch results just like the API ones to test view model.
 *
 * @property moviesCatalogRepoImp MoviesCatalogRepoImp
 * @property mainActivityViewModel MoviesCatalogViewModel
 * @property MOVIES_JSON_DATA_FOR_TESTING String
 */
@RunWith(JUnit4::class)
class MoviesCatalogViewModelTest {

    @Mock
    private lateinit var moviesCatalogRepoImp: MoviesCatalogRepoImp

    private lateinit var mainActivityViewModel: MoviesCatalogViewModel

    @Before
    fun setUp() {
        println("Setting it up")
        MockitoAnnotations.initMocks(this)
        mainActivityViewModel = MoviesCatalogViewModel(moviesCatalogRepoImp)
    }


    @Test
    fun fetchMoviesFromServerForSuccessResponse() {
        CoroutineScope(Dispatchers.Default).launch {

            /***
             * ARRANGE - setup the testing objects and prepare the prerequisites for your test.
             */
            `when`(
                moviesCatalogRepoImp.getMovies(isFiltered = false, time = System.currentTimeMillis(), page = 1)
            ).thenReturn(
                Response.success(
                    200,
                    getMoviesTestData()
                )
            )

            val observer = mock(Observer::class.java) as Observer<List<Movie>>
            mainActivityViewModel.movies.observeForever(observer)

            /**
             * ACT - perform the actual work of the test.
             */
            mainActivityViewModel.fetchMovies()

            /**
             * ASSERT - verify the result.
             */

            assertNotNull(mainActivityViewModel.movies.value)
        }


    }


    @Test
    fun fetchMoviesFromServerForErrorResponse() {
        CoroutineScope(Dispatchers.Default).launch {

            /***
             * ARRANGE - setup the testing objects and prepare the prerequisites for your test.
             */
            `when`(
                moviesCatalogRepoImp.getMovies(isFiltered = false, time = System.currentTimeMillis(), page = 1)
            ).thenReturn(

                Response.error(
                    400, ResponseBody.create(
                        "application/json".toMediaTypeOrNull(),
                        "{\"error_message\":[\"Do you even lift?\"]}"
                    )
                )

            )

            val observer = mock(Observer::class.java) as Observer<List<Movie>>
            mainActivityViewModel.movies.observeForever(observer)

            /**
             * ACT - perform the actual work of the test.
             */
            mainActivityViewModel.fetchMovies()

            /**
             * ASSERT - verify the result.
             */

            assertNotNull(mainActivityViewModel.movies.value)
        }


    }


    @After
    fun finish() {
        println("Testing finished")
    }


    /**
     * Get movies test data response just like the API for mocking
     * @return MoviesCatalog
     */
    private fun getMoviesTestData(): MoviesCatalog {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(MoviesCatalog::class.java)

        return jsonAdapter.fromJson(MOVIES_JSON_DATA_FOR_TESTING)!!

    }


    private val MOVIES_JSON_DATA_FOR_TESTING: String = "{\n" +
            "  \"page\": 1,\n" +
            "  \"total_results\": 422877,\n" +
            "  \"total_pages\": 21144,\n" +
            "  \"results\": [\n" +
            "    {\n" +
            "      \"vote_count\": 469,\n" +
            "      \"id\": 301528,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 7.6,\n" +
            "      \"title\": \"Toy Story 4\",\n" +
            "      \"popularity\": 320.361,\n" +
            "      \"poster_path\": \"/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Toy Story 4\",\n" +
            "      \"genre_ids\": [\n" +
            "        12,\n" +
            "        16,\n" +
            "        35,\n" +
            "        10751\n" +
            "      ],\n" +
            "      \"backdrop_path\": \"/m67smI1IIMmYzCl9axvKNULVKLr.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \\\"Forky\\\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.\",\n" +
            "      \"release_date\": \"2019-06-19\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"vote_count\": 990,\n" +
            "      \"id\": 320288,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 6.3,\n" +
            "      \"title\": \"Dark Phoenix\",\n" +
            "      \"popularity\": 186.459,\n" +
            "      \"poster_path\": \"/kZv92eTc0Gg3mKxqjjDAM73z9cy.jpg\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Dark Phoenix\",\n" +
            "      \"genre_ids\": [\n" +
            "        878,\n" +
            "        28\n" +
            "      ],\n" +
            "      \"backdrop_path\": \"/phxiKFDvPeQj4AbkvJLmuZEieDU.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"The X-Men face their most formidable and powerful foe when one of their own, Jean Grey, starts to spiral out of control. During a rescue mission in outer space, Jean is nearly killed when she's hit by a mysterious cosmic force. Once she returns home, this force not only makes her infinitely more powerful, but far more unstable. The X-Men must now band together to save her soul and battle aliens that want to use Grey's new abilities to rule the galaxy.\",\n" +
            "      \"release_date\": \"2019-06-05\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"vote_count\": 29,\n" +
            "      \"id\": 480042,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 4.8,\n" +
            "      \"title\": \"Escape Plan: The Extractors\",\n" +
            "      \"popularity\": 81.64,\n" +
            "      \"poster_path\": \"/9V5fl1mGgVZzOYod0Jq2hyRlzEY.jpg\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Escape Plan: The Extractors\",\n" +
            "      \"genre_ids\": [\n" +
            "        28,\n" +
            "        53\n" +
            "      ],\n" +
            "      \"backdrop_path\": \"/ygWKYTu7OnZKF9H5NsgjL9iURGV.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"After security expert Ray Breslin is hired to rescue the kidnapped daughter of a Hong Kong tech mogul from a formidable Latvian prison, Breslin's girlfriend is also captured. Now he and his team must pull off a deadly rescue mission to confront their sadistic foe and save the hostages before time runs out.\",\n" +
            "      \"release_date\": \"2019-06-20\"\n" +
            "    }\n" +
            "  ]\n" +
            "}"
}