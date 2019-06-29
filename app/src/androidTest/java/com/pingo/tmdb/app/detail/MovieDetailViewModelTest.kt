package com.pingo.tmdb.app.detail

import androidx.lifecycle.Observer
import com.pingo.tmdb.app.detail.data.MovieDetailRepoImp
import com.pingo.tmdb.shared.models.MovieDetail
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
 *  Testing [MovieDetailViewModel]. Mocking [MovieDetailRepoImp] to fetch results just like the API ones to test view model.
 *
 * @property movieDetailRepoImp MovieDetailRepoImp
 * @property mainActivityViewModel MovieDetailViewModel
 * @property MOVIES_JSON_DATA_FOR_TESTING String
 */
@RunWith(JUnit4::class)
class MovieDetailViewModelTest {

    @Mock
    private lateinit var movieDetailRepoImp: MovieDetailRepoImp

    private lateinit var mainActivityViewModel: MovieDetailViewModel

    @Before
    fun setUp() {
        println("Setting it up")
        MockitoAnnotations.initMocks(this)
        mainActivityViewModel = MovieDetailViewModel(movieDetailRepoImp)
    }


    @Test
    fun fetchMoviesFromServerForSuccessResponse() {
        CoroutineScope(Dispatchers.Default).launch {

            /***
             * ARRANGE - setup the testing objects and prepare the prerequisites for your test.
             */
            `when`(
                movieDetailRepoImp.getMovies(movieId = "299537") // assuming that the item clicked has movie id of 299537
            ).thenReturn(
                Response.success(
                    200,
                    getMovieDetailTestData()
                )
            )

            val observer = mock(Observer::class.java) as Observer<MovieDetail>
            mainActivityViewModel.movieDetail.observeForever(observer)

            /**
             * ACT - perform the actual work of the test.
             */
            mainActivityViewModel.getMovieDetails()

            /**
             * ASSERT - verify the result.
             */

            assertNotNull(mainActivityViewModel.movieDetail.value)
        }


    }


    @Test
    fun fetchMoviesFromServerForErrorResponse() {
        CoroutineScope(Dispatchers.Default).launch {
            /***
             * ARRANGE - setup the testing objects and prepare the prerequisites for your test.
             */
            `when`(
                movieDetailRepoImp.getMovies(movieId = "299537") // assuming that the item clicked has movie id of 299537
            ).thenReturn(
                Response.error(
                    400, ResponseBody.create(
                        "application/json".toMediaTypeOrNull(),
                        "{\"error_message\":[\"Do you even lift?\"]}"
                    )
                )
            )

            val observer = mock(Observer::class.java) as Observer<MovieDetail>
            mainActivityViewModel.movieDetail.observeForever(observer)

            /**
             * ACT - perform the actual work of the test.
             */
            mainActivityViewModel.getMovieDetails()

            /**
             * ASSERT - verify the result.
             */

            assertNotNull(mainActivityViewModel.movieDetail.value)
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
    private fun getMovieDetailTestData(): MovieDetail {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(MovieDetail::class.java)

        return jsonAdapter.fromJson(MOVIES_JSON_DATA_FOR_TESTING)!!

    }


    private val MOVIES_JSON_DATA_FOR_TESTING: String = "{\n" +
            "  \"adult\": false,\n" +
            "  \"backdrop_path\": \"/w2PMyoyLU22YvrGK3smVM9fW1jj.jpg\",\n" +
            "  \"belongs_to_collection\": null,\n" +
            "  \"budget\": 152000000,\n" +
            "  \"genres\": [\n" +
            "    {\n" +
            "      \"id\": 28,\n" +
            "      \"name\": \"Action\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 12,\n" +
            "      \"name\": \"Adventure\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 878,\n" +
            "      \"name\": \"Science Fiction\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"homepage\": \"https://www.marvel.com/movies/captain-marvel\",\n" +
            "  \"id\": 299537,\n" +
            "  \"imdb_id\": \"tt4154664\",\n" +
            "  \"original_language\": \"en\",\n" +
            "  \"original_title\": \"Captain Marvel\",\n" +
            "  \"overview\": \"The story follows Carol Danvers as she becomes one of the universe’s most powerful heroes when Earth is caught in the middle of a galactic war between two alien races. Set in the 1990s, Captain Marvel is an all-new adventure from a previously unseen period in the history of the Marvel Cinematic Universe.\",\n" +
            "  \"popularity\": 123.314,\n" +
            "  \"poster_path\": \"/AtsgWhDnHTq68L0lLsUrCnM7TjG.jpg\",\n" +
            "  \"production_companies\": [\n" +
            "    {\n" +
            "      \"id\": 420,\n" +
            "      \"logo_path\": \"/hUzeosd33nzE5MCNsZxCGEKTXaQ.png\",\n" +
            "      \"name\": \"Marvel Studios\",\n" +
            "      \"origin_country\": \"US\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"logo_path\": \"/wdrCwmRnLFJhEoH8GSfymY85KHT.png\",\n" +
            "      \"name\": \"Walt Disney Pictures\",\n" +
            "      \"origin_country\": \"US\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"production_countries\": [\n" +
            "    {\n" +
            "      \"iso_3166_1\": \"US\",\n" +
            "      \"name\": \"United States of America\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"release_date\": \"2019-03-06\",\n" +
            "  \"revenue\": 1127287899,\n" +
            "  \"runtime\": 124,\n" +
            "  \"spoken_languages\": [\n" +
            "    {\n" +
            "      \"iso_639_1\": \"en\",\n" +
            "      \"name\": \"English\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"Released\",\n" +
            "  \"tagline\": \"Higher. Further. Faster.\",\n" +
            "  \"title\": \"Captain Marvel\",\n" +
            "  \"video\": false,\n" +
            "  \"vote_average\": 7,\n" +
            "  \"vote_count\": 5981,\n" +
            "  \"videos\": {\n" +
            "    \"results\": [\n" +
            "      {\n" +
            "        \"id\": \"5c8ad92b9251415249c0b099\",\n" +
            "        \"iso_639_1\": \"en\",\n" +
            "        \"iso_3166_1\": \"US\",\n" +
            "        \"key\": \"Y_JGZTlUbZg\",\n" +
            "        \"name\": \"“Born Free” TV Spot\",\n" +
            "        \"site\": \"YouTube\",\n" +
            "        \"size\": 1080,\n" +
            "        \"type\": \"Teaser\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"5c8ad94d9251410ce2c3d039\",\n" +
            "        \"iso_639_1\": \"en\",\n" +
            "        \"iso_3166_1\": \"US\",\n" +
            "        \"key\": \"NCoPycawxUk\",\n" +
            "        \"name\": \"“Big Game” TV Spot\",\n" +
            "        \"site\": \"YouTube\",\n" +
            "        \"size\": 1080,\n" +
            "        \"type\": \"Teaser\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"5c8ad97b9251410ceac3f0b5\",\n" +
            "        \"iso_639_1\": \"en\",\n" +
            "        \"iso_3166_1\": \"US\",\n" +
            "        \"key\": \"lkJKyRjsuck\",\n" +
            "        \"name\": \"“Rise” TV Spot\",\n" +
            "        \"site\": \"YouTube\",\n" +
            "        \"size\": 1080,\n" +
            "        \"type\": \"Teaser\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"5cecb99d92514175e8bedc6e\",\n" +
            "        \"iso_639_1\": \"en\",\n" +
            "        \"iso_3166_1\": \"US\",\n" +
            "        \"key\": \"eh_qPcQoFK0\",\n" +
            "        \"name\": \"Pre-Order Now!\",\n" +
            "        \"site\": \"YouTube\",\n" +
            "        \"size\": 720,\n" +
            "        \"type\": \"Trailer\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}"
}