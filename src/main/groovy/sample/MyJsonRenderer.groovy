package sample

import grails.converters.JSON
import grails.rest.render.RenderContext
import org.grails.plugins.web.rest.render.json.DefaultJsonRenderer

class MyJsonRenderer<T> extends DefaultJsonRenderer<T> {

    String key

    MyJsonRenderer(Class<T> targetType) {
        super(targetType)
    }

    @Override
    protected void renderJson(T object, RenderContext context) {
        def detail = context.arguments.detail ?: 'short'
        JSON.use(detail) {
            def converter = object as MyJson
            converter.key = key
            renderJson(converter, context)
        }
    }
}
