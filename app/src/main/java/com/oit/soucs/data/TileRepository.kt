package com.oit.soucs.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TileRepository @Inject constructor(private val tileDao: TileDao) {
    fun getTiles() = tileDao.getTiles()
    fun getTile(tileId: String) = tileDao.getTile(tileId)
}