package fr.appsolute.tp.data.database

import androidx.room.*
import fr.appsolute.tp.RickAndMortyApplication
import fr.appsolute.tp.data.model.Episode

@Database(entities = [Episode::class], exportSchema = false, version = 1)
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract val episodeDao: EpisodeDao
}

private class DatabaseManagerImpl(override val database: RickAndMortyDatabase) : DatabaseManager

interface DatabaseManager {
    val database: RickAndMortyDatabase

    companion object {
        private const val DATABASE_NAME = "rick_and_morty.db"
        @Volatile
        private var databaseManager: DatabaseManager? = null

        fun getInstance(app: RickAndMortyApplication? = null): DatabaseManager {
            return databaseManager ?: synchronized(this) {
                DatabaseManagerImpl(
                    Room.databaseBuilder(
                        app ?: throw IllegalStateException("No Application"),
                        RickAndMortyDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                ).also {
                    databaseManager = it
                }
            }
        }
    }
}

@Dao
interface EpisodeDao {

    @Query("SElECT COUNT(*) FROM episode")
    fun getCount(): Int

    @Query("SELECT * FROM episode where id = :episodeId ")
    fun selectById(episodeId: Int): Episode

    @Query("SELECT * FROM episode")
    fun selectAll(): List<Episode>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(entities: List<Episode>)
}