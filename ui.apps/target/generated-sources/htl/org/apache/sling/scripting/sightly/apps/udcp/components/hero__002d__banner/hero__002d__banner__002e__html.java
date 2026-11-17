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
package org.apache.sling.scripting.sightly.apps.udcp.components.hero__002d__banner;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class hero__002d__banner__002e__html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_templates = null;
Object _dynamic_properties = bindings.get("properties");
out.write("\uFEFF");
_global_templates = renderContext.call("use", "core/wcm/components/commons/v1/templates.html", obj());
out.write("\n\n");
{
    Object var_testvariable4 = renderContext.getObjectModel().resolveProperty(_dynamic_properties, "headline");
    if (renderContext.getObjectModel().toBoolean(var_testvariable4)) {
        out.write("<section class=\"cmp-hero-banner\"");
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
        out.write(">\n    ");
        {
            Object var_testvariable5 = renderContext.getObjectModel().resolveProperty(_dynamic_properties, "fileReference");
            if (renderContext.getObjectModel().toBoolean(var_testvariable5)) {
                out.write("<div class=\"cmp-hero-banner__image\" aria-hidden=\"true\">\n        <img");
                {
                    Object var_attrvalue6 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "fileReference"), "uri");
                    {
                        boolean var_shoulddisplayattr9 = ((!"".equals(var_attrvalue6)) && (!((Object)false).equals(var_attrvalue6)));
                        if (var_shoulddisplayattr9) {
                            out.write(" src");
                            {
                                boolean var_istrueattr8 = (var_attrvalue6.equals(true));
                                if (!var_istrueattr8) {
                                    out.write("=\"");
                                    out.write(renderContext.getObjectModel().toString(var_attrvalue6));
                                    out.write("\"");
                                }
                            }
                        }
                    }
                }
                out.write(" alt=\"\" fetchpriority=\"high\"/>\n    </div>");
            }
        }
        out.write("\n\n    <div class=\"cmp-hero-banner__content\">\n        ");
        {
            Object var_testvariable10 = renderContext.getObjectModel().resolveProperty(_dynamic_properties, "eyebrow");
            if (renderContext.getObjectModel().toBoolean(var_testvariable10)) {
                out.write("<p class=\"cmp-hero-banner__eyebrow\">");
                {
                    Object var_11 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "eyebrow"), "text");
                    out.write(renderContext.getObjectModel().toString(var_11));
                }
                out.write("</p>");
            }
        }
        out.write("\n\n        <h1 class=\"cmp-hero-banner__title\">");
        {
            String var_12 = (("\n            " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "headline"), "text"))) + "\n            ");
            out.write(renderContext.getObjectModel().toString(var_12));
        }
        {
            Object var_testvariable13 = renderContext.getObjectModel().resolveProperty(_dynamic_properties, "headlineHighlight");
            if (renderContext.getObjectModel().toBoolean(var_testvariable13)) {
                out.write("<span>");
                {
                    Object var_14 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "headlineHighlight"), "text");
                    out.write(renderContext.getObjectModel().toString(var_14));
                }
                out.write("</span>");
            }
        }
        out.write("\n        </h1>\n\n        ");
        {
            Object var_testvariable15 = renderContext.getObjectModel().resolveProperty(_dynamic_properties, "description");
            if (renderContext.getObjectModel().toBoolean(var_testvariable15)) {
                out.write("<p class=\"cmp-hero-banner__description\">");
                {
                    Object var_16 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "description"), "text");
                    out.write(renderContext.getObjectModel().toString(var_16));
                }
                out.write("</p>");
            }
        }
        out.write("\n\n        ");
        {
            Object var_testvariable17 = ((renderContext.getObjectModel().toBoolean(((renderContext.getObjectModel().toBoolean(renderContext.getObjectModel().resolveProperty(_dynamic_properties, "ctaText")) ? renderContext.getObjectModel().resolveProperty(_dynamic_properties, "ctaLink") : renderContext.getObjectModel().resolveProperty(_dynamic_properties, "ctaText")))) ? ((renderContext.getObjectModel().toBoolean(renderContext.getObjectModel().resolveProperty(_dynamic_properties, "ctaText")) ? renderContext.getObjectModel().resolveProperty(_dynamic_properties, "ctaLink") : renderContext.getObjectModel().resolveProperty(_dynamic_properties, "ctaText"))) : ((renderContext.getObjectModel().toBoolean(renderContext.getObjectModel().resolveProperty(_dynamic_properties, "secondaryCtaText")) ? renderContext.getObjectModel().resolveProperty(_dynamic_properties, "secondaryCtaLink") : renderContext.getObjectModel().resolveProperty(_dynamic_properties, "secondaryCtaText")))));
            if (renderContext.getObjectModel().toBoolean(var_testvariable17)) {
                out.write("<div class=\"cmp-hero-banner__actions\">\n            ");
                {
                    Object var_testvariable18 = ((renderContext.getObjectModel().toBoolean(renderContext.getObjectModel().resolveProperty(_dynamic_properties, "ctaText")) ? renderContext.getObjectModel().resolveProperty(_dynamic_properties, "ctaLink") : renderContext.getObjectModel().resolveProperty(_dynamic_properties, "ctaText")));
                    if (renderContext.getObjectModel().toBoolean(var_testvariable18)) {
                        out.write("<a class=\"cmp-hero-banner__cta\"");
                        {
                            Object var_attrvalue19 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "ctaLink"), "uri");
                            {
                                boolean var_shoulddisplayattr22 = ((!"".equals(var_attrvalue19)) && (!((Object)false).equals(var_attrvalue19)));
                                if (var_shoulddisplayattr22) {
                                    out.write(" href");
                                    {
                                        boolean var_istrueattr21 = (var_attrvalue19.equals(true));
                                        if (!var_istrueattr21) {
                                            out.write("=\"");
                                            out.write(renderContext.getObjectModel().toString(var_attrvalue19));
                                            out.write("\"");
                                        }
                                    }
                                }
                            }
                        }
                        out.write(">");
                        {
                            Object var_23 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "ctaText"), "text");
                            out.write(renderContext.getObjectModel().toString(var_23));
                        }
                        out.write("</a>");
                    }
                }
                out.write("\n\n            ");
                {
                    Object var_testvariable24 = ((renderContext.getObjectModel().toBoolean(renderContext.getObjectModel().resolveProperty(_dynamic_properties, "secondaryCtaText")) ? renderContext.getObjectModel().resolveProperty(_dynamic_properties, "secondaryCtaLink") : renderContext.getObjectModel().resolveProperty(_dynamic_properties, "secondaryCtaText")));
                    if (renderContext.getObjectModel().toBoolean(var_testvariable24)) {
                        out.write("<a class=\"cmp-hero-banner__cta cmp-hero-banner__cta--secondary\"");
                        {
                            Object var_attrvalue25 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "secondaryCtaLink"), "uri");
                            {
                                boolean var_shoulddisplayattr28 = ((!"".equals(var_attrvalue25)) && (!((Object)false).equals(var_attrvalue25)));
                                if (var_shoulddisplayattr28) {
                                    out.write(" href");
                                    {
                                        boolean var_istrueattr27 = (var_attrvalue25.equals(true));
                                        if (!var_istrueattr27) {
                                            out.write("=\"");
                                            out.write(renderContext.getObjectModel().toString(var_attrvalue25));
                                            out.write("\"");
                                        }
                                    }
                                }
                            }
                        }
                        out.write(">");
                        {
                            Object var_29 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "secondaryCtaText"), "text");
                            out.write(renderContext.getObjectModel().toString(var_29));
                        }
                        out.write("</a>");
                    }
                }
                out.write("\n        </div>");
            }
        }
        out.write("\n    </div>\n</section>");
    }
}
out.write("\n\n");
{
    Object var_templatevar30 = renderContext.getObjectModel().resolveProperty(_global_templates, "placeholder");
    {
        boolean var_templateoptions31_field$_isempty = (!renderContext.getObjectModel().toBoolean(renderContext.getObjectModel().resolveProperty(_dynamic_properties, "headline")));
        {
            java.util.Map var_templateoptions31 = obj().with("isEmpty", var_templateoptions31_field$_isempty);
            callUnit(out, renderContext, var_templatevar30, var_templateoptions31);
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

