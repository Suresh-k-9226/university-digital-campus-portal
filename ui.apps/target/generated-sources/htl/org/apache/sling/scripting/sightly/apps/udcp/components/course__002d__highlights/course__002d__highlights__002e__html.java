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
package org.apache.sling.scripting.sightly.apps.udcp.components.course__002d__highlights;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class course__002d__highlights__002e__html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_templates = null;
Object _global_coursehighlights = null;
Collection var_collectionvar2_list_coerced$ = null;
out.write("\uFEFF");
_global_templates = renderContext.call("use", "core/wcm/components/commons/v1/templates.html", obj());
out.write("\n");
_global_coursehighlights = renderContext.call("use", com.university.udcp.core.models.CourseHighlightsModelImpl.class.getName(), obj());
out.write("\n\n");
{
    Object var_testvariable0 = renderContext.getObjectModel().resolveProperty(_global_coursehighlights, "courses");
    if (renderContext.getObjectModel().toBoolean(var_testvariable0)) {
        out.write("<section class=\"cmp-course-highlights\">\n    <header class=\"cmp-course-highlights__header\">\n        <span class=\"cmp-course-highlights__eyebrow\">ACADEMICS</span>\n        <h2 class=\"cmp-course-highlights__title\">Explore Our Academic Programs</h2>\n        <p class=\"cmp-course-highlights__intro\">\n            Find the right program to pursue your ambitions and build your future.\n        </p>\n    </header>\n\n    <p class=\"cmp-course-highlights__count\">");
        {
            String var_1 = (("\n        Showing " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(renderContext.getObjectModel().resolveProperty(_global_coursehighlights, "courses"), "size"), "text"))) + " academic programs\n    ");
            out.write(renderContext.getObjectModel().toString(var_1));
        }
        out.write("</p>\n\n    ");
        {
            Object var_collectionvar2 = renderContext.getObjectModel().resolveProperty(_global_coursehighlights, "courses");
            {
                long var_size3 = ((var_collectionvar2_list_coerced$ == null ? (var_collectionvar2_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar2)) : var_collectionvar2_list_coerced$).size());
                {
                    boolean var_notempty4 = (var_size3 > 0);
                    if (var_notempty4) {
                        {
                            long var_end7 = var_size3;
                            {
                                boolean var_validstartstepend8 = (((0 < var_size3) && true) && (var_end7 > 0));
                                if (var_validstartstepend8) {
                                    out.write("<div class=\"cmp-course-highlights__list\">");
                                    if (var_collectionvar2_list_coerced$ == null) {
                                        var_collectionvar2_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar2);
                                    }
                                    long var_index9 = 0;
                                    for (Object course : var_collectionvar2_list_coerced$) {
                                        {
                                            boolean var_traversal11 = (((var_index9 >= 0) && (var_index9 <= var_end7)) && true);
                                            if (var_traversal11) {
                                                out.write("\n        <article class=\"cmp-course-highlights__item\">\n            <div class=\"cmp-course-highlights__top\">\n                ");
                                                {
                                                    Object var_testvariable12 = renderContext.getObjectModel().resolveProperty(course, "school");
                                                    if (renderContext.getObjectModel().toBoolean(var_testvariable12)) {
                                                        out.write("<span class=\"cmp-course-highlights__school\">");
                                                        {
                                                            Object var_13 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(course, "school"), "text");
                                                            out.write(renderContext.getObjectModel().toString(var_13));
                                                        }
                                                        out.write("</span>");
                                                    }
                                                }
                                                out.write("\n                ");
                                                {
                                                    Object var_testvariable14 = renderContext.getObjectModel().resolveProperty(course, "duration");
                                                    if (renderContext.getObjectModel().toBoolean(var_testvariable14)) {
                                                        out.write("<span class=\"cmp-course-highlights__duration\">");
                                                        {
                                                            Object var_15 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(course, "duration"), "text");
                                                            out.write(renderContext.getObjectModel().toString(var_15));
                                                        }
                                                        out.write("</span>");
                                                    }
                                                }
                                                out.write("\n            </div>\n\n            <h3 class=\"cmp-course-highlights__name\">");
                                                {
                                                    Object var_16 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(course, "courseName"), "text");
                                                    out.write(renderContext.getObjectModel().toString(var_16));
                                                }
                                                out.write("</h3>\n\n            ");
                                                {
                                                    Object var_testvariable17 = renderContext.getObjectModel().resolveProperty(course, "fees");
                                                    if (renderContext.getObjectModel().toBoolean(var_testvariable17)) {
                                                        out.write("<p class=\"cmp-course-highlights__fees\">");
                                                        {
                                                            String var_18 = (("\n                \u20B9" + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(course, "fees"), "text"))) + "\n            ");
                                                            out.write(renderContext.getObjectModel().toString(var_18));
                                                        }
                                                        out.write("</p>");
                                                    }
                                                }
                                                out.write("\n\n            <div class=\"cmp-course-highlights__section\">\n                <h4>About this program</h4>\n                <p>");
                                                {
                                                    Object var_19 = renderContext.call("xss", ((renderContext.getObjectModel().toBoolean(renderContext.getObjectModel().resolveProperty(course, "description")) ? renderContext.getObjectModel().resolveProperty(course, "description") : "Program information will be updated soon.")), "text");
                                                    out.write(renderContext.getObjectModel().toString(var_19));
                                                }
                                                out.write("</p>\n            </div>\n\n            ");
                                                {
                                                    Object var_testvariable20 = renderContext.getObjectModel().resolveProperty(course, "eligibility");
                                                    if (renderContext.getObjectModel().toBoolean(var_testvariable20)) {
                                                        out.write("<div class=\"cmp-course-highlights__section cmp-course-highlights__eligibility\">\n                <h4>Eligibility</h4>\n                <p>");
                                                        {
                                                            Object var_21 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(course, "eligibility"), "text");
                                                            out.write(renderContext.getObjectModel().toString(var_21));
                                                        }
                                                        out.write("</p>\n            </div>");
                                                    }
                                                }
                                                out.write("\n\n            <div class=\"cmp-course-highlights__footer\">\n                <span>University of Velen</span>\n                <span aria-hidden=\"true\">\u2192</span>\n            </div>\n        </article>\n    ");
                                            }
                                        }
                                        var_index9++;
                                    }
                                    out.write("</div>");
                                }
                            }
                        }
                    }
                }
            }
            var_collectionvar2_list_coerced$ = null;
        }
        out.write("\n</section>");
    }
}
out.write("\n\n");
{
    Object var_templatevar22 = renderContext.getObjectModel().resolveProperty(_global_templates, "placeholder");
    {
        boolean var_templateoptions23_field$_isempty = (!renderContext.getObjectModel().toBoolean(renderContext.getObjectModel().resolveProperty(_global_coursehighlights, "courses")));
        {
            java.util.Map var_templateoptions23 = obj().with("isEmpty", var_templateoptions23_field$_isempty);
            callUnit(out, renderContext, var_templatevar22, var_templateoptions23);
        }
    }
}
out.write("\r\n");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

