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
package org.apache.sling.scripting.sightly.apps.udcp.components.faculty__002d__profile;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class faculty__002d__profile__002e__html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_templates = null;
Object _global_facultyprofiles = null;
Object _dynamic_properties = bindings.get("properties");
Collection var_collectionvar5_list_coerced$ = null;
out.write("\uFEFF");
_global_templates = renderContext.call("use", "core/wcm/components/commons/v1/templates.html", obj());
out.write("\n");
_global_facultyprofiles = renderContext.call("use", com.university.udcp.core.models.FacultyProfilesModelImpl.class.getName(), obj());
out.write("\n\n");
{
    Object var_testvariable4 = renderContext.getObjectModel().resolveProperty(renderContext.getObjectModel().resolveProperty(_global_facultyprofiles, "facultyMembers"), "size");
    if (renderContext.getObjectModel().toBoolean(var_testvariable4)) {
        out.write("<section class=\"cmp-faculty-profiles\"");
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
        out.write(">\n    <header class=\"cmp-faculty-profiles__header\">\n        <span class=\"cmp-faculty-profiles__eyebrow\">OUR PEOPLE</span>\n        <h2 class=\"cmp-faculty-profiles__title\">Meet Our Faculty</h2>\n        <p class=\"cmp-faculty-profiles__intro\">\n            Learn from accomplished educators and researchers dedicated to your success.\n        </p>\n    </header>\n\n    ");
        {
            Object var_collectionvar5 = renderContext.getObjectModel().resolveProperty(_global_facultyprofiles, "facultyMembers");
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
                                    out.write("<div class=\"cmp-faculty-profiles__grid\">");
                                    if (var_collectionvar5_list_coerced$ == null) {
                                        var_collectionvar5_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar5);
                                    }
                                    long var_index12 = 0;
                                    for (Object faculty : var_collectionvar5_list_coerced$) {
                                        {
                                            boolean var_traversal14 = (((var_index12 >= 0) && (var_index12 <= var_end10)) && true);
                                            if (var_traversal14) {
                                                out.write("\n        <article class=\"cmp-faculty-profile\">\n            <div class=\"cmp-faculty-profile__profile\">\n                <div class=\"cmp-faculty-profile__avatar\" aria-hidden=\"true\">\n                    ");
                                                {
                                                    Object var_testvariable15 = renderContext.getObjectModel().resolveProperty(faculty, "facultyPhoto");
                                                    if (renderContext.getObjectModel().toBoolean(var_testvariable15)) {
                                                        out.write("<img");
                                                        {
                                                            Object var_attrvalue16 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(faculty, "facultyPhoto"), "uri");
                                                            {
                                                                boolean var_shoulddisplayattr19 = ((!"".equals(var_attrvalue16)) && (!((Object)false).equals(var_attrvalue16)));
                                                                if (var_shoulddisplayattr19) {
                                                                    out.write(" src");
                                                                    {
                                                                        boolean var_istrueattr18 = (var_attrvalue16.equals(true));
                                                                        if (!var_istrueattr18) {
                                                                            out.write("=\"");
                                                                            out.write(renderContext.getObjectModel().toString(var_attrvalue16));
                                                                            out.write("\"");
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        out.write(" alt=\"\" loading=\"lazy\"/>");
                                                    }
                                                }
                                                out.write("\n                    ");
                                                {
                                                    boolean var_testvariable20 = (!renderContext.getObjectModel().toBoolean(renderContext.getObjectModel().resolveProperty(faculty, "facultyPhoto")));
                                                    if (var_testvariable20) {
                                                        out.write("<span>");
                                                        {
                                                            Object var_21 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(faculty, "initials"), "text");
                                                            out.write(renderContext.getObjectModel().toString(var_21));
                                                        }
                                                        out.write("</span>");
                                                    }
                                                }
                                                out.write("\n                </div>\n\n                <div class=\"cmp-faculty-profile__identity\">\n                    <h3 class=\"cmp-faculty-profile__name\">");
                                                {
                                                    Object var_22 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(faculty, "facultyName"), "text");
                                                    out.write(renderContext.getObjectModel().toString(var_22));
                                                }
                                                out.write("</h3>\n                    ");
                                                {
                                                    Object var_testvariable23 = renderContext.getObjectModel().resolveProperty(faculty, "designation");
                                                    if (renderContext.getObjectModel().toBoolean(var_testvariable23)) {
                                                        out.write("<p class=\"cmp-faculty-profile__designation\">");
                                                        {
                                                            Object var_24 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(faculty, "designation"), "text");
                                                            out.write(renderContext.getObjectModel().toString(var_24));
                                                        }
                                                        out.write("</p>");
                                                    }
                                                }
                                                out.write("\n                </div>\n            </div>\n\n            <dl class=\"cmp-faculty-profile__details\">\n                ");
                                                {
                                                    Object var_testvariable25 = renderContext.getObjectModel().resolveProperty(faculty, "school");
                                                    if (renderContext.getObjectModel().toBoolean(var_testvariable25)) {
                                                        out.write("<div>\n                    <dt>Department</dt>\n                    <dd>");
                                                        {
                                                            Object var_26 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(faculty, "school"), "text");
                                                            out.write(renderContext.getObjectModel().toString(var_26));
                                                        }
                                                        out.write("</dd>\n                </div>");
                                                    }
                                                }
                                                out.write("\n                ");
                                                {
                                                    Object var_testvariable27 = renderContext.getObjectModel().resolveProperty(faculty, "qualification");
                                                    if (renderContext.getObjectModel().toBoolean(var_testvariable27)) {
                                                        out.write("<div>\n                    <dt>Qualification</dt>\n                    <dd>");
                                                        {
                                                            Object var_28 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(faculty, "qualification"), "text");
                                                            out.write(renderContext.getObjectModel().toString(var_28));
                                                        }
                                                        out.write("</dd>\n                </div>");
                                                    }
                                                }
                                                out.write("\n                ");
                                                {
                                                    Object var_testvariable29 = renderContext.getObjectModel().resolveProperty(faculty, "experience");
                                                    if (renderContext.getObjectModel().toBoolean(var_testvariable29)) {
                                                        out.write("<div>\n                    <dt>Experience</dt>\n                    <dd>");
                                                        {
                                                            String var_30 = (renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(faculty, "experience"), "text")) + " years of experience");
                                                            out.write(renderContext.getObjectModel().toString(var_30));
                                                        }
                                                        out.write("</dd>\n                </div>");
                                                    }
                                                }
                                                out.write("\n            </dl>\n\n            ");
                                                {
                                                    Object var_testvariable31 = renderContext.getObjectModel().resolveProperty(faculty, "biography");
                                                    if (renderContext.getObjectModel().toBoolean(var_testvariable31)) {
                                                        out.write("<div class=\"cmp-faculty-profile__biography\">\n                <h4>Biography</h4>\n                <p>");
                                                        {
                                                            Object var_32 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(faculty, "biography"), "text");
                                                            out.write(renderContext.getObjectModel().toString(var_32));
                                                        }
                                                        out.write("</p>\n            </div>");
                                                    }
                                                }
                                                out.write("\n\n            <footer class=\"cmp-faculty-profile__footer\">\n                ");
                                                {
                                                    Object var_testvariable33 = renderContext.getObjectModel().resolveProperty(faculty, "email");
                                                    if (renderContext.getObjectModel().toBoolean(var_testvariable33)) {
                                                        out.write("<a");
                                                        {
                                                            String var_attrcontent34 = ("mailto:" + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(faculty, "email"), "uri")));
                                                            out.write(" href=\"");
                                                            out.write(renderContext.getObjectModel().toString(var_attrcontent34));
                                                            out.write("\"");
                                                        }
                                                        out.write(">");
                                                        {
                                                            Object var_35 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(faculty, "email"), "text");
                                                            out.write(renderContext.getObjectModel().toString(var_35));
                                                        }
                                                        out.write("</a>");
                                                    }
                                                }
                                                out.write("\n                ");
                                                {
                                                    boolean var_testvariable36 = (!renderContext.getObjectModel().toBoolean(renderContext.getObjectModel().resolveProperty(faculty, "email")));
                                                    if (var_testvariable36) {
                                                        out.write("<span>Email not available</span>");
                                                    }
                                                }
                                                out.write("\n            </footer>\n        </article>\n    ");
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
        out.write("\n</section>");
    }
}
out.write("\n\n");
{
    Object var_templatevar37 = renderContext.getObjectModel().resolveProperty(_global_templates, "placeholder");
    {
        boolean var_templateoptions38_field$_isempty = (!renderContext.getObjectModel().toBoolean(renderContext.getObjectModel().resolveProperty(renderContext.getObjectModel().resolveProperty(_global_facultyprofiles, "facultyMembers"), "size")));
        {
            java.util.Map var_templateoptions38 = obj().with("isEmpty", var_templateoptions38_field$_isempty);
            callUnit(out, renderContext, var_templatevar37, var_templateoptions38);
        }
    }
}
out.write("\n");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

