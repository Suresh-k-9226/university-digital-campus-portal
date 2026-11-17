/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.sling.scripting.sightly.apps.udcp.components.upcoming__002d__events;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class upcoming__002d__events__002e__html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_templates = null;
Object _global_eventsmodel = null;
Object _dynamic_properties = bindings.get("properties");
Collection var_collectionvar5_list_coerced$ = null;
out.write("\uFEFF");
_global_templates = renderContext.call("use", "core/wcm/components/commons/v1/templates.html", obj());
out.write("\n");
_global_eventsmodel = renderContext.call("use", com.university.udcp.core.models.UpcomingEventsModelImpl.class.getName(), obj());
out.write("\n\n");
{
    Object var_testvariable4 = renderContext.getObjectModel().resolveProperty(_global_eventsmodel, "events");
    if (renderContext.getObjectModel().toBoolean(var_testvariable4)) {
        out.write("<section class=\"cmp-upcoming-events\"");
        {
            Object var_attrvalue_id0 = renderContext.getObjectModel().resolveProperty(_dynamic_properties, "id");
            {
                Object var_attrvalueescaped_id1 = renderContext.call("xss", var_attrvalue_id0, "attribute", "id");
                {
                    boolean var_shoulddisplayattr_id3 = (((null != var_attrvalueescaped_id1) && (!"".equals(var_attrvalueescaped_id1))) && ((!"".equals(var_attrvalue_id0)) && (!((Object)false).equals(var_attrvalue_id0))));
                    if (var_shoulddisplayattr_id3) {
                        out.write(" id");
                        {
                            boolean var_istruevalue_id2 = (var_attrvalue_id0.equals(true));
                            if (!var_istruevalue_id2) {
                                out.write("=\"");
                                out.write(renderContext.getObjectModel().toString(var_attrvalueescaped_id1));
                                out.write("\"");
                            }
                        }
                    }
                }
            }
        }
        out.write(">\n\n    <header class=\"cmp-upcoming-events__header\">\n        <span class=\"cmp-upcoming-events__eyebrow\">CAMPUS LIFE</span>\n        <h2 class=\"cmp-upcoming-events__title\">Upcoming Events</h2>\n        <p class=\"cmp-upcoming-events__intro\">\n            Join the conversations, celebrations and experiences happening across our university.\n        </p>\n    </header>\n\n    ");
        {
            Object var_collectionvar5 = renderContext.getObjectModel().resolveProperty(_global_eventsmodel, "events");
            {
                long var_size6 = ((var_collectionvar5_list_coerced$ == null ? (var_collectionvar5_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar5)) : var_collectionvar5_list_coerced$).size());
                {
                    boolean var_notempty7 = (var_size6 > 0);
                    if (var_notempty7) {
                        {
                            long var_end10 = var_size6;
                            {
                                boolean var_validstartstepend11 = (((0 < var_size6) && true) && (var_end10 > 0));
                                if (var_validstartstepend11) {
                                    out.write("<div class=\"cmp-upcoming-events__list\">");
                                    if (var_collectionvar5_list_coerced$ == null) {
                                        var_collectionvar5_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar5);
                                    }
                                    long var_index12 = 0;
                                    for (Object event : var_collectionvar5_list_coerced$) {
                                        {
                                            boolean var_traversal14 = (((var_index12 >= 0) && (var_index12 <= var_end10)) && true);
                                            if (var_traversal14) {
                                                out.write("\n        <article class=\"cmp-upcoming-events__item\">\n            <div class=\"cmp-upcoming-events__art\" aria-hidden=\"true\">\n                <span>UNIVERSITY OF VELEN</span>\n            </div>\n            <div class=\"cmp-upcoming-events__content\">\n                <div class=\"cmp-upcoming-events__topline\">\n                    ");
                                                {
                                                    Object var_testvariable15 = renderContext.getObjectModel().resolveProperty(event, "category");
                                                    if (renderContext.getObjectModel().toBoolean(var_testvariable15)) {
                                                        out.write("<span class=\"cmp-upcoming-events__category\">");
                                                        {
                                                            String var_16 = (("\n                        " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(event, "category"), "text"))) + "\n                    ");
                                                            out.write(renderContext.getObjectModel().toString(var_16));
                                                        }
                                                        out.write("</span>");
                                                    }
                                                }
                                                out.write("\n                    ");
                                                {
                                                    Object var_testvariable17 = renderContext.getObjectModel().resolveProperty(event, "date");
                                                    if (renderContext.getObjectModel().toBoolean(var_testvariable17)) {
                                                        out.write("<time class=\"cmp-upcoming-events__date\">");
                                                        {
                                                            String var_18 = (("\n                        " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(event, "date"), "text"))) + "\n                    ");
                                                            out.write(renderContext.getObjectModel().toString(var_18));
                                                        }
                                                        out.write("</time>");
                                                    }
                                                }
                                                out.write("\n                </div>\n\n                <h3 class=\"cmp-upcoming-events__name\">");
                                                {
                                                    Object var_19 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(event, "title"), "text");
                                                    out.write(renderContext.getObjectModel().toString(var_19));
                                                }
                                                out.write("</h3>\n\n                <dl class=\"cmp-upcoming-events__details\">\n                    ");
                                                {
                                                    Object var_testvariable20 = renderContext.getObjectModel().resolveProperty(event, "time");
                                                    if (renderContext.getObjectModel().toBoolean(var_testvariable20)) {
                                                        out.write("<div>\n                        <dt>Time</dt>\n                        <dd>");
                                                        {
                                                            Object var_21 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(event, "time"), "text");
                                                            out.write(renderContext.getObjectModel().toString(var_21));
                                                        }
                                                        out.write("</dd>\n                    </div>");
                                                    }
                                                }
                                                out.write("\n                    ");
                                                {
                                                    Object var_testvariable22 = renderContext.getObjectModel().resolveProperty(event, "venue");
                                                    if (renderContext.getObjectModel().toBoolean(var_testvariable22)) {
                                                        out.write("<div>\n                        <dt>Venue</dt>\n                        <dd>");
                                                        {
                                                            Object var_23 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(event, "venue"), "text");
                                                            out.write(renderContext.getObjectModel().toString(var_23));
                                                        }
                                                        out.write("</dd>\n                    </div>");
                                                    }
                                                }
                                                out.write("\n                </dl>\n\n                ");
                                                {
                                                    Object var_testvariable24 = renderContext.getObjectModel().resolveProperty(event, "description");
                                                    if (renderContext.getObjectModel().toBoolean(var_testvariable24)) {
                                                        out.write("<p class=\"cmp-upcoming-events__description\">");
                                                        {
                                                            String var_25 = (("\n                    " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(event, "description"), "text"))) + "\n                ");
                                                            out.write(renderContext.getObjectModel().toString(var_25));
                                                        }
                                                        out.write("</p>");
                                                    }
                                                }
                                                out.write("\n\n                ");
                                                {
                                                    Object var_testvariable26 = renderContext.getObjectModel().resolveProperty(event, "registrationUrl");
                                                    if (renderContext.getObjectModel().toBoolean(var_testvariable26)) {
                                                        out.write("<a class=\"cmp-upcoming-events__registration\"");
                                                        {
                                                            Object var_attrvalue27 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(event, "registrationUrl"), "uri");
                                                            {
                                                                boolean var_shoulddisplayattr30 = ((!"".equals(var_attrvalue27)) && (!((Object)false).equals(var_attrvalue27)));
                                                                if (var_shoulddisplayattr30) {
                                                                    out.write(" href");
                                                                    {
                                                                        boolean var_istrueattr29 = (var_attrvalue27.equals(true));
                                                                        if (!var_istrueattr29) {
                                                                            out.write("=\"");
                                                                            out.write(renderContext.getObjectModel().toString(var_attrvalue27));
                                                                            out.write("\"");
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        out.write(">\n                    Register for event\n                    <span aria-hidden=\"true\">\u2192</span>\n                </a>");
                                                    }
                                                }
                                                out.write("\n            </div>\n        </article>\n    ");
                                            }
                                        }
                                        var_index12++;
                                    }
                                    out.write("</div>");
                                }
                            }
                        }
                    }
                }
            }
            var_collectionvar5_list_coerced$ = null;
        }
        out.write("\n\n</section>");
    }
}
out.write("\n\n");
{
    Object var_templatevar31 = renderContext.getObjectModel().resolveProperty(_global_templates, "placeholder");
    {
        boolean var_templateoptions32_field$_isempty = (!renderContext.getObjectModel().toBoolean(renderContext.getObjectModel().resolveProperty(_global_eventsmodel, "events")));
        {
            java.util.Map var_templateoptions32 = obj().with("isEmpty", var_templateoptions32_field$_isempty);
            callUnit(out, renderContext, var_templatevar31, var_templateoptions32);
        }
    }
}


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

