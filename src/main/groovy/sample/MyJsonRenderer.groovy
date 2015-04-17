package sample

import grails.converters.JSON
import grails.rest.render.RenderContext
import org.grails.plugins.web.rest.render.json.DefaultJsonRenderer

class MyJsonRenderer<T> extends DefaultJsonRenderer<T> {

    MyJsonRenderer(Class<T> targetType) {
        super(targetType)
    }

    @Override
    protected void renderJson(T object, RenderContext context) {
        def detail = context.arguments.detail ?: 'short'
        JSON.use(detail) {
            renderJson(object as JSON, context)
        }
    }
}
