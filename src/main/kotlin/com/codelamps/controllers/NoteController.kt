package com.codelamps.controllers

import com.codelamps.database.model.Note
import com.codelamps.database.repository.NoteRepository
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant


// POST http://localhost:8080/notes
// GET http://localhost:8080/notes?ownerId=123


@RestController
@RequestMapping("/notes")
class NoteController(
    private val repository: NoteRepository
){

    data class NoteRequest(
        val id: String?,
        val title: String,
        val content: String,
        val color: Long,
        val ownerId: String
    )

    data class NoteResponse(
        val id: String,
        val title: String,
        val content: String,
        val color: Long,
        val createdAt: Instant
    )

    @PostMapping
    fun save(body: NoteRequest): NoteResponse{
        val note = repository.save(
            Note(
                id = body.id?.let { ObjectId(it) } ?: ObjectId.get(),
                title = body.title,
                content = body.content,
                color = body.color,
                createdAt = Instant.now(),
                ownerId = ObjectId(body.ownerId)
            )
        )

        return note.toResponse()
    }

    @GetMapping
    fun findByOwnerId(
        @RequestParam(required = true) ownerId: String
    ): List<NoteResponse>{
        return repository.findByOwnerId(ObjectId(ownerId)).map {
            it.toResponse()
        }
    }


    private fun Note.toResponse(): NoteController.NoteResponse{
        return NoteResponse(
                id = id.toHexString(),
                title = title,
                content = content,
                color = color,
                createdAt = createdAt
            )
    }
}