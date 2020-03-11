package com.example.dagger2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

  @Insert(onConflict=OnConflictStrategy.REPLACE)
  long[] insertMovies(List<MovieEntity> movies);

  @Query("SELECT * FROM `MovieEntity`")
  List<MovieEntity> getMoviesByPage();
}
