package com.example.dagger2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

  public abstract MovieDao movieDao();
}
