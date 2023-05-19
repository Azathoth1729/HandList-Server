package com.azathoth.handlistserver.spacenode.model

import jakarta.persistence.*


@Entity(name = "spaceNode")
data class SpaceNode(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    var path: String,

    @Enumerated(EnumType.STRING)
    var nodetype: SpaceNodeType = SpaceNodeType.List,
)