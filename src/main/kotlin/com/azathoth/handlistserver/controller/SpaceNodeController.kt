package com.azathoth.handlistserver.controller

import com.azathoth.handlistserver.config.AppConfig.Companion.API_VERSION
import com.azathoth.handlistserver.model.spacenode.SpaceNode
import com.azathoth.handlistserver.model.spacenode.SpaceNodeService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/$API_VERSION/spacenodes")
class SpaceNodeController(val service: SpaceNodeService) {
    @GetMapping
    fun getAll(@RequestParam path: String?) =
        if (path == null) {
            service.getAll()
        } else {
            service.findByPath(path)
        }

    @GetMapping("/{node_id}")
    fun findById(@PathVariable("node_id") nodeId: Long) = service.findById(nodeId)

    @PostMapping
    fun insert(@RequestBody node: SpaceNode) = service.insert(node)

    @PostMapping("/many")
    fun insertAll(@RequestBody nodes: List<SpaceNode>) = service.insertAll(nodes)

    @DeleteMapping("/{node_id}")
    fun delete(@PathVariable("node_id") nodeId: Long) = service.deleteById(nodeId)

    @PutMapping("/{node_id}")
    fun update(@PathVariable("node_id") nodeId: Long, @RequestBody node: SpaceNode) =
        service.updateById(nodeId, node)
}