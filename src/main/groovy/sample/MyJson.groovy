package sample

import grails.converters.JSON
import groovy.util.logging.Commons
import org.grails.web.converters.Converter
import org.grails.web.converters.exceptions.ConverterException
import org.grails.web.json.JSONWriter
import org.grails.web.json.PathCapturingJSONWriterWrapper
import org.grails.web.json.PrettyPrintJSONWriter

@Commons
class MyJson extends JSON {

    String key
    Map paging

    MyJson() {
    }

    MyJson(Object target) {
        super(target)
    }

    @Override
    void render(Writer out) throws ConverterException {
        prepareRender(out)
        try {
            if (key) {
                writer.object()
                writer.key(key)
                value(target)
                if (paging) {
                    writer.key('paging')
                    value(paging)
                }
                writer.endObject()
            } else {
                value(target)
            }
        } finally {
            finalizeRender(out)
        }
    }

    private void prepareRender(Writer out) {
        writer = prettyPrint ? new PrettyPrintJSONWriter(out) : new JSONWriter(out)
        if (circularReferenceBehaviour == Converter.CircularReferenceBehaviour.PATH) {
            if (log.isInfoEnabled()) {
                log.info(String.format("Using experimental CircularReferenceBehaviour.PATH for %s", getClass().getName()))
            }
            writer = new PathCapturingJSONWriterWrapper(writer)
        }
        referenceStack = new Stack<Object>()
    }

    private void finalizeRender(Writer out) {
        try {
            out.flush()
            out.close()
        }
        catch (Exception e) {
            log.warn("Unexpected exception while closing a writer: " + e.getMessage())
        }
    }
}
