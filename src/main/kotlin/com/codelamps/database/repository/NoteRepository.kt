package com.codelamps.database.repository

import com.codelamps.database.model.Note
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface NoteRepository : MongoRepository<Note, ObjectId> {

    //    fun findByTitleContains(title: String): List<Note>
    fun findByOwnerId(ownerId: ObjectId): List<Note>
}