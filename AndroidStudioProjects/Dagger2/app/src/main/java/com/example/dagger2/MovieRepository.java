package com.example.dagger2;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Observable;

import javax.inject.Singleton;

@Singleton
public class MovieRepository {
  private MovieDao movieDao;
  private MovieApiService movieApiService;
  public MovieRepository(MovieDao movieDao,
                         MovieApiService movieApiService) {
    this.movieDao = movieDao;
    this.movieApiService = movieApiService;
  }



  /*
   * We are using this method to fetch the movies list
   * NetworkBoundResource is part of the Android architecture
   * components. You will notice that this is a modified version of
   * that class. That class is based on LiveData but here we are
   * using Observable from RxJava.
   *
   * There are three methods called:
   * a. fetch data from server
   * b. fetch data from local
   * c. save data from api in local
   *
   * So basically we fetch data from server, store it locally
   * and then fetch data from local and update the UI with
   * this data.
   *
   * */
  public Observable<Resource<List<MovieEntity>>> loadMoviesByType() {
    return new NetworkBoundResource<List<MovieEntity>, MovieApiResponse>() {

      @Override
      protected void saveCallResult(@NonNull MovieApiResponse item) {
        movieDao.insertMovies(item.getResults());
      }

      @Override
      protected boolean shouldFetch() {
        return true;
      }

      @NonNull
      @Override
      protected Flowable<List<MovieEntity>> loadFromDb() {
        List<MovieEntity> movieEntities = movieDao.getMoviesByPage();
        if(movieEntities == null || movieEntities.isEmpty()) {
          return Flowable.empty();
        }
        return Flowable.just(movieEntities);
      }

      @NonNull
      @Override
      protected Observable<Resource<MovieApiResponse>> createCall() {
        return movieApiService.fetchMovies()
                .flatMap(movieApiResponse -> Observable.just(movieApiResponse == null
                        ? Resource.error("", new MovieApiResponse())
                        : Resource.success(movieApiResponse)));
      }
    }.getAsObservable();
  }

}
