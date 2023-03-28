package com.azathoth.handlistserver.model.spacenode

import jakarta.persistence.*


@Entity(name = "spaceNode")
class SpaceNode(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    var path: String,

    @Enumerated(EnumType.STRING)
    var nodetype: SpaceNodeType = SpaceNodeType.List,

//    @OneToMany(mappedBy = "spaceNode",cascade = [CascadeType.ALL], targetEntity = Task::class)
//    var tasks: MutableSet<Task> = mutableSetOf()
)