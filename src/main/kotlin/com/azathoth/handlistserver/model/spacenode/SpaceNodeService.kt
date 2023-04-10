package com.azathoth.handlistserver.model.spacenode

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SpaceNodeService(@Autowired val repo: SpaceNodeRepository) {

    fun getAll() = repo.findAll()

    fun findByPath(path: String) = repo.findByPath(path)

    fun findById(id: Long): SpaceNode =
        repo.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun insert(node: SpaceNode): SpaceNode = repo.save(node)

    fun insertAll(nodes: Iterable<SpaceNode>) = repo.saveAll(nodes)

    fun deleteById(id: Long) =
        if (repo.existsById(id)) repo.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun updateById(id: Long, node: SpaceNode): SpaceNode =
        if (repo.existsById(id)) {
            node.id = id
            repo.save(node)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)


}