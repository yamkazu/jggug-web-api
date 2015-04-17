package sample

import grails.rest.render.ContainerRenderer

class MyJsonCollectionRenderer extends MyJsonRenderer implements ContainerRenderer {

    final Class componentType

    MyJsonCollectionRenderer(Class componentType) {
        super(Collection)
        this.componentType = componentType
    }
}
