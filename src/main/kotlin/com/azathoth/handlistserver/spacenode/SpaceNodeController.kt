package com.azathoth.handlistserver.spacenode

import com.azathoth.handlistserver.config.AppConfig.Companion.API_VERSION
import com.azathoth.handlistserver.spacenode.model.SpaceNode
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/$API_VERSION/spacenodes")
class SpaceNodeController(val service: SpaceNodeService) {
    @GetMapping
    fun findAll() = service.findAll()

    @GetMapping("/{node_id}")
    fun findById(@PathVariable("node_id") nodeId: Long) = service.findById(nodeId)

    @PostMapping
    fun insert(@RequestBody node: SpaceNode) = service.insert(node)

    @PostMapping("/many")
    fun insertAll(@RequestBody nodes: List<SpaceNode>) = service.insertAll(nodes)

    @PutMapping("/{node_id}")
    fun update(@PathVariable("node_id") nodeId: Long, @RequestBody node: SpaceNode) =
        service.updateById(nodeId, node)

    @DeleteMapping("/{node_id}")
    fun delete(@PathVariable("node_id") nodeId: Long) = service.deleteById(nodeId)

//    @DeleteMapping("/{node_path}/sub")
//    fun deleteSubPaths(@PathVariable("node_path") path: String) = service.deleteSubPathsOf(path)

    @DeleteMapping("/{node_id}/sub")
    fun deleteSubPaths(@PathVariable("node_id") nodeId: Long) = service.deleteSubPathsById(nodeId)

}